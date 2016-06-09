/*******************************************************************************
 *    ___                  _   ____  ____
 *   / _ \ _   _  ___  ___| |_|  _ \| __ )
 *  | | | | | | |/ _ \/ __| __| | | |  _ \
 *  | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *   \__\_\\__,_|\___||___/\__|____/|____/
 *
 * Copyright (C) 2014-2016 Appsicle
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

package com.questdb.ql.impl.join;

import com.questdb.ex.JournalException;
import com.questdb.factory.JournalReaderFactory;
import com.questdb.factory.configuration.RecordColumnMetadata;
import com.questdb.factory.configuration.RecordMetadata;
import com.questdb.misc.Misc;
import com.questdb.ql.*;
import com.questdb.ql.impl.NullRecord;
import com.questdb.ql.impl.SplitRecordMetadata;
import com.questdb.ql.impl.join.hash.FakeRecord;
import com.questdb.ql.impl.join.hash.MultiRecordMap;
import com.questdb.ql.impl.map.MultiMap;
import com.questdb.ql.ops.AbstractCombinedRecordSource;
import com.questdb.std.CharSink;
import com.questdb.std.IntList;
import com.questdb.std.ObjHashSet;
import com.questdb.std.ObjList;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.Closeable;
import java.io.IOException;

public class HashJoinRecordSource extends AbstractCombinedRecordSource implements Closeable {
    private final RecordSource master;
    private final RecordSource slave;
    private final SplitRecordMetadata metadata;
    private final SplitRecord currentRecord;
    private final SplitRecordStorageFacade storageFacade;
    private final ObjList<RecordColumnMetadata> masterColumns = new ObjList<>();
    private final ObjList<RecordColumnMetadata> slaveColumns = new ObjList<>();
    private final IntList masterColIndex;
    private final IntList slaveColIndex;
    private final FakeRecord fakeRecord = new FakeRecord();
    private final boolean byRowId;
    private final boolean outer;
    private final MultiRecordMap recordMap;
    private RecordCursor slaveCursor;
    private RecordCursor masterCursor;
    private RecordCursor hashTableCursor;

    @SuppressFBWarnings({"PRMC_POSSIBLY_REDUNDANT_METHOD_CALLS"})
    public HashJoinRecordSource(
            RecordSource master,
            IntList masterColIndices,
            RecordSource slave,
            IntList slaveColIndices,
            boolean outer,
            int keyPageSize,
            int dataPageSize,
            int rowIdPageSize
    ) {
        this.master = master;
        this.slave = slave;
        this.metadata = new SplitRecordMetadata(master.getMetadata(), slave.getMetadata());
        this.currentRecord = new SplitRecord(metadata, master.getMetadata().getColumnCount());
        this.byRowId = slave.supportsRowIdAccess();
        this.masterColIndex = masterColIndices;
        this.slaveColIndex = slaveColIndices;
        this.recordMap = createRecordMap(master, slave, keyPageSize, dataPageSize, rowIdPageSize);
        this.outer = outer;
        this.storageFacade = new SplitRecordStorageFacade(metadata, master.getMetadata().getColumnCount());
    }

    @Override
    public void close() throws IOException {
        Misc.free(recordMap);
        Misc.free(master);
        Misc.free(slave);
    }

    @Override
    public RecordMetadata getMetadata() {
        return metadata;
    }

    @Override
    public RecordCursor prepareCursor(JournalReaderFactory factory, CancellationHandler cancellationHandler) throws JournalException {
        this.slaveCursor = slave.prepareCursor(factory, cancellationHandler);
        this.masterCursor = master.prepareCursor(factory, cancellationHandler);
        buildHashTable(cancellationHandler);
        recordMap.setStorageFacade(slaveCursor.getStorageFacade());
        storageFacade.prepare(factory, masterCursor.getStorageFacade(), slaveCursor.getStorageFacade());
        return this;
    }

    @Override
    public void reset() {
        hashTableCursor = null;
        master.reset();
        recordMap.clear();
    }

    @Override
    public boolean supportsRowIdAccess() {
        return false;
    }

    @Override
    public StorageFacade getStorageFacade() {
        return storageFacade;
    }

    @Override
    public Record newRecord() {
        return null;
    }

    @Override
    public Record recordAt(long rowId) {
        return null;
    }

    @Override
    public void recordAt(Record record, long atRowId) {
    }

    @Override
    public boolean hasNext() {
        if (hashTableCursor != null && hashTableCursor.hasNext()) {
            Record rec = hashTableCursor.next();
            currentRecord.setB(byRowId ? slaveCursor.recordAt(rec.getLong(0)) : rec);
            return true;
        }
        return hasNext0();
    }

    @SuppressFBWarnings({"IT_NO_SUCH_ELEMENT"})
    @Override
    public SplitRecord next() {
        return currentRecord;
    }

    @Override
    public void toSink(CharSink sink) {
        sink.put('{');
        sink.putQuoted("op").put(':').putQuoted("HashJoinRecordSource").put(',');
        sink.putQuoted("master").put(':').put(master).put(',');
        sink.putQuoted("slave").put(':').put(slave).put(',');
        sink.putQuoted("joinOn").put(':').put('[');
        sink.put('[');
        for (int i = 0, n = masterColumns.size(); i < n; i++) {
            if (i > 0) {
                sink.put(',');
            }
            sink.putQuoted(masterColumns.getQuick(i).getName());
        }
        sink.put(']').put(',');
        sink.put('[');
        for (int i = 0, n = slaveColumns.size(); i < n; i++) {
            if (i > 0) {
                sink.put(',');
            }
            sink.putQuoted(slaveColumns.getQuick(i).getName());
        }
        sink.put("]]}");
    }

    private void buildHashTable(CancellationHandler cancellationHandler) {
        for (Record r : slaveCursor) {
            cancellationHandler.check();
            MultiMap.KeyWriter key = recordMap.claimKey();
            for (int i = 0, k = slaveColumns.size(); i < k; i++) {
                key.put(r, slaveColIndex.getQuick(i), slaveColumns.getQuick(i).getType());
            }
            if (byRowId) {
                recordMap.add(key, fakeRecord.of(r.getRowId()));
            } else {
                recordMap.add(key, r);
            }
        }
    }

    private MultiRecordMap createRecordMap(RecordSource masterSource,
                                           RecordSource slaveSource,
                                           int keyPageSize,
                                           int dataPageSize,
                                           int rowIdPageSize) {
        RecordMetadata mm = masterSource.getMetadata();
        for (int i = 0, k = masterColIndex.size(); i < k; i++) {
            this.masterColumns.add(mm.getColumnQuick(masterColIndex.getQuick(i)));
        }

        RecordMetadata sm = slaveSource.getMetadata();
        ObjHashSet<String> keyCols = new ObjHashSet<>();
        for (int i = 0, k = slaveColIndex.size(); i < k; i++) {
            int index = slaveColIndex.getQuick(i);
            this.slaveColumns.add(sm.getColumnQuick(index));
            keyCols.add(sm.getColumnName(index));
        }
        return byRowId ? new MultiRecordMap(sm, keyCols, fakeRecord.getMetadata(), keyPageSize, rowIdPageSize) :
                new MultiRecordMap(sm, keyCols, slaveSource.getMetadata(), keyPageSize, dataPageSize);
    }

    private boolean hasNext0() {
        while (masterCursor.hasNext()) {
            Record r = masterCursor.next();
            currentRecord.setA(r);

            MultiMap.KeyWriter key = recordMap.claimKey();

            for (int i = 0, k = masterColumns.size(); i < k; i++) {
                key.put(r, masterColIndex.getQuick(i), masterColumns.getQuick(i).getType());
            }

            hashTableCursor = recordMap.get(key);

            if (hashTableCursor.hasNext()) {
                if (byRowId) {
                    currentRecord.setB(slaveCursor.recordAt(hashTableCursor.next().getLong(0)));
                } else {
                    currentRecord.setB(hashTableCursor.next());
                }
                return true;
            } else if (outer) {
                hashTableCursor = null;
                currentRecord.setB(NullRecord.INSTANCE);
                return true;
            }
        }
        return false;
    }
}
