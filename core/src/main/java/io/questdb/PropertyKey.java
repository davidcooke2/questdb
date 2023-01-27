/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2022 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum PropertyKey {
    BINARYDATA_ENCODING_MAXLENGTH("binarydata.encoding.maxlength"),
    CAIRO_ROOT("cairo.root"),
    CAIRO_SNAPSHOT_INSTANCE_ID("cairo.snapshot.instance.id"),
    CAIRO_SNAPSHOT_RECOVERY_ENABLED("cairo.snapshot.recovery.enabled"),
    CAIRO_MKDIR_MODE("cairo.mkdir.mode"),
    CAIRO_WRITER_ALTER_BUSY_WAIT_TIMEOUT("cairo.writer.alter.busy.wait.timeout"),
    CAIRO_WRITER_ALTER_MAX_WAIT_TIMEOUT("cairo.writer.alter.max.wait.timeout"),
    CAIRO_WRITER_TICK_ROWS_COUNT("cairo.writer.tick.rows.count"),
    CAIRO_WRITER_COMMAND_QUEUE_CAPACITY("cairo.writer.command.queue.capacity"),
    CAIRO_SQL_BACKUP_DIR_DATETIME_FORMAT("cairo.sql.backup.dir.datetime.format"),
    CAIRO_SQL_JIT_MODE("cairo.sql.jit.mode"),
    CAIRO_COMMIT_MODE("cairo.commit.mode"),
    CAIRO_CREATE_AS_SELECT_RETRY_COUNT("cairo.create.as.select.retry.count"),
    CAIRO_DEFAULT_MAP_TYPE("cairo.default.map.type"),
    CAIRO_DEFAULT_SYMBOL_CACHE_FLAG("cairo.default.symbol.cache.flag"),
    CAIRO_DEFAULT_SYMBOL_CAPACITY("cairo.default.symbol.capacity"),
    CAIRO_FILE_OPERATION_RETRY_COUNT("cairo.file.operation.retry.count"),
    CAIRO_IDLE_CHECK_INTERVAL("cairo.idle.check.interval"),
    CAIRO_INACTIVE_READER_TTL("cairo.inactive.reader.ttl"),
    CAIRO_INACTIVE_WRITER_TTL("cairo.inactive.writer.ttl"),
    CAIRO_INDEX_VALUE_BLOCK_SIZE("cairo.index.value.block.size"),
    CAIRO_MAX_SWAP_FILE_COUNT("cairo.max.swap.file.count"),
    CAIRO_PARALLEL_INDEX_THRESHOLD("cairo.parallel.index.threshold"),
    CAIRO_READER_POOL_MAX_SEGMENTS("cairo.reader.pool.max.segments"),
    CAIRO_SPIN_LOCK_TIMEOUT("cairo.spin.lock.timeout"),
    CAIRO_CHARACTER_STORE_CAPACITY("cairo.character.store.capacity"),
    CAIRO_CHARACTER_STORE_SEQUENCE_POOL_CAPACITY("cairo.character.store.sequence.pool.capacity"),
    CAIRO_COLUMN_POOL_CAPACITY("cairo.column.pool.capacity"),
    CAIRO_COMPACT_MAP_LOAD_FACTOR("cairo.compact.map.load.factor"),
    CAIRO_EXPRESSION_POOL_CAPACITY("cairo.expression.pool.capacity"),
    CAIRO_FAST_MAP_LOAD_FACTOR("cairo.fast.map.load.factor"),
    CAIRO_SQL_JOIN_CONTEXT_POOL_CAPACITY("cairo.sql.join.context.pool.capacity"),
    CAIRO_LEXER_POOL_CAPACITY("cairo.lexer.pool.capacity"),
    CAIRO_SQL_MAP_KEY_CAPACITY("cairo.sql.map.key.capacity"),
    CAIRO_SQL_SMALL_MAP_KEY_CAPACITY("cairo.sql.small.map.key.capacity"),
    CAIRO_SQL_SMALL_MAP_PAGE_SIZE("cairo.sql.small.map.page.size"),
    CAIRO_SQL_MAP_PAGE_SIZE("cairo.sql.map.page.size"),
    CAIRO_SQL_MAP_MAX_PAGES("cairo.sql.map.max.pages"),
    CAIRO_SQL_MAP_MAX_RESIZES("cairo.sql.map.max.resizes"),
    CAIRO_MODEL_POOL_CAPACITY("cairo.model.pool.capacity"),
    CAIRO_SQL_MAX_NEGATIVE_LIMIT("cairo.sql.max.negative.limit"),
    CAIRO_SQL_SORT_KEY_PAGE_SIZE("cairo.sql.sort.key.page.size"),
    CAIRO_SQL_SORT_KEY_MAX_PAGES("cairo.sql.sort.key.max.pages"),
    CAIRO_SQL_SORT_LIGHT_VALUE_PAGE_SIZE("cairo.sql.sort.light.value.page.size"),
    CAIRO_SQL_SORT_LIGHT_VALUE_MAX_PAGES("cairo.sql.sort.light.value.max.pages"),
    CAIRO_SQL_HASH_JOIN_VALUE_PAGE_SIZE("cairo.sql.hash.join.value.page.size"),
    CAIRO_SQL_HASH_JOIN_VALUE_MAX_PAGES("cairo.sql.hash.join.value.max.pages"),
    CAIRO_SQL_LATEST_BY_ROW_COUNT("cairo.sql.latest.by.row.count"),
    CAIRO_SQL_HASH_JOIN_LIGHT_VALUE_PAGE_SIZE("cairo.sql.hash.join.light.value.page.size"),
    CAIRO_SQL_HASH_JOIN_LIGHT_VALUE_MAX_PAGES("cairo.sql.hash.join.light.value.max.pages"),
    CAIRO_SQL_SORT_VALUE_PAGE_SIZE("cairo.sql.sort.value.page.size"),
    CAIRO_SQL_SORT_VALUE_MAX_PAGES("cairo.sql.sort.value.max.pages"),
    CAIRO_WORK_STEAL_TIMEOUT_NANOS("cairo.work.steal.timeout.nanos"),
    CAIRO_PARALLEL_INDEXING_ENABLED("cairo.parallel.indexing.enabled"),
    CAIRO_PAGE_FRAME_REDUCE_QUEUE_CAPACITY("cairo.page.frame.reduce.queue.capacity"),
    CAIRO_PAGE_FRAME_ROWID_LIST_CAPACITY("cairo.page.frame.rowid.list.capacity"),
    CAIRO_PAGE_FRAME_COLUMN_LIST_CAPACITY("cairo.page.frame.column.list.capacity"),
    CAIRO_SQL_PARALLEL_FILTER_ENABLED("cairo.sql.parallel.filter.enabled"),
    CAIRO_SQL_PARALLEL_FILTER_PRETOUCH_ENABLED("cairo.sql.parallel.filter.pretouch.enabled"),
    CAIRO_PAGE_FRAME_SHARD_COUNT("cairo.page.frame.shard.count"),
    CAIRO_PAGE_FRAME_TASK_POOL_CAPACITY("cairo.page.frame.task.pool.capacity"),
    CAIRO_SQL_JOIN_METADATA_PAGE_SIZE("cairo.sql.join.metadata.page.size"),
    CAIRO_SQL_JOIN_METADATA_MAX_RESIZES("cairo.sql.join.metadata.max.resizes"),
    CAIRO_SQL_ANALYTIC_COLUMN_POOL_CAPACITY("cairo.sql.analytic.column.pool.capacity"),
    CAIRO_SQL_CREATE_TABEL_MODEL_POOL_CAPACITY("cairo.sql.create.table.model.pool.capacity"),
    CAIRO_SQL_COLUMN_CAST_MODEL_POOL_CAPACITY("cairo.sql.column.cast.model.pool.capacity"),
    CAIRO_SQL_RENAME_TABLE_MODEL_POOL_CAPACITY("cairo.sql.rename.table.model.pool.capacity"),
    CAIRO_SQL_WITH_CLAUSE_MODEL_POOL_CAPACITY("cairo.sql.with.clause.model.pool.capacity"),
    CAIRO_SQL_INSERT_MODEL_POOL_CAPACITY("cairo.sql.insert.model.pool.capacity"),
    CAIRO_WRITER_DATA_INDEX_KEY_APPEND_PAGE_SIZE("cairo.writer.data.index.key.append.page.size"),
    CAIRO_WRITER_DATA_INDEX_VALUE_APPEND_PAGE_SIZE("cairo.writer.data.index.value.append.page.size"),
    CAIRO_WRITER_DATA_APPEND_PAGE_SIZE("cairo.writer.data.append.page.size"),
    CAIRO_WRITER_MISC_APPEND_PAGE_SIZE("cairo.writer.misc.append.page.size"),
    CAIRO_WRITER_COMMAND_QUEUE_SLOT_SIZE("cairo.writer.command.queue.slot.size"),
    CAIRO_SQL_SAMPLEBY_PAGE_SIZE("cairo.sql.sampleby.page.size"),
    CAIRO_SQL_DOUBLE_CAST_SCALE("cairo.sql.double.cast.scale"),
    CAIRO_SQL_FLOAT_CAST_SCALE("cairo.sql.float.cast.scale"),
    CAIRO_SQL_GROUPBY_MAP_CAPACITY("cairo.sql.groupby.map.capacity"),
    CAIRO_SQL_GROUPBY_POOL_CAPACITY("cairo.sql.groupby.pool.capacity"),
    CAIRO_SQL_MAX_SYMBOL_NOT_EQUALS_COUNT("cairo.sql.max.symbol.not.equals.count"),
    CAIRO_SQL_BIND_VARIABLE_POOL_SIZE("cairo.sql.bind.variable.pool.size"),
    CAIRO_DATE_LOCALE("cairo.date.locale"),
    CAIRO_SQL_DISTINCT_TIMESTAMP_KEY_CAPACITY("cairo.sql.distinct.timestamp.key.capacity"),
    CAIRO_SQL_DISTINCT_TIMESTAMP_LOAD_FACTOR("cairo.sql.distinct.timestamp.load.factor"),
    CAIRO_SQL_PAGE_FRAME_MIN_ROWS("cairo.sql.page.frame.min.rows"),
    CAIRO_SQL_PAGE_FRAME_MAX_ROWS("cairo.sql.page.frame.max.rows"),
    CAIRO_SQL_JIT_IR_MEMORY_PAGE_SIZE("cairo.sql.jit.ir.memory.page.size"),
    CAIRO_SQL_JIT_IR_MEMORY_MAX_PAGES("cairo.sql.jit.ir.memory.max.pages"),
    CAIRO_SQL_JIT_BIND_VARS_MEMORY_PAGE_SIZE("cairo.sql.jit.bind.vars.memory.page.size"),
    CAIRO_SQL_JIT_BIND_VARS_MEMORY_MAX_PAGES("cairo.sql.jit.bind.vars.memory.max.pages"),
    CAIRO_SQL_JIT_ROWS_THRESHOLD("cairo.sql.jit.rows.threshold"),
    CAIRO_SQL_JIT_PAGE_ADDRESS_CACHE_THRESHOLD("cairo.sql.jit.page.address.cache.threshold"),
    CAIRO_SQL_JIT_DEBUG_ENABLED("cairo.sql.jit.debug.enabled"),
    CAIRO_WRITER_FO_OPTS("cairo.writer.fo_opts"),
    CAIRO_SQL_COPY_FORMATS_FILE("cairo.sql.copy.formats.file"),
    CAIRO_SQL_COPY_MODEL_POOL_CAPACITY("cairo.sql.copy.model.pool.capacity"),
    CAIRO_SQL_COPY_BUFFER_SIZE("cairo.sql.copy.buffer.size"),
    CAIRO_SQL_COPY_ROOT("cairo.sql.copy.root"),
    CAIRO_SQL_COPY_WORK_ROOT("cairo.sql.copy.work.root"),
    CAIRO_SQL_COPY_MAX_INDEX_CHUNK_SIZE("cairo.sql.copy.max.index.chunk.size"),
    CAIRO_SQL_COPY_QUEUE_CAPACITY("cairo.sql.copy.queue.capacity"),
    CAIRO_SQL_COPY_LOG_RETENTION_DAYS("cairo.sql.copy.log.retention.days"),
    CAIRO_SQL_EXPLAIN_MODEL_POOL_CAPACITY("cairo.sql.explain.model.pool.capacity"),
    CAIRO_O3_MIN_LAG("cairo.o3.min.lag"),
    CAIRO_SQL_BACKUP_ROOT("cairo.sql.backup.root"),
    CAIRO_ATTACH_PARTITION_SUFFIX("cairo.attach.partition.suffix"),
    CAIRO_ATTACH_PARTITION_COPY("cairo.attach.partition.copy"),
    CAIRO_SQL_BACKUP_DIR_TMP_NAME("cairo.sql.backup.dir.tmp.name"),
    CAIRO_SQL_BACKUP_MKDIR_MODE("cairo.sql.backup.mkdir.mode"),
    CAIRO_COLUMN_INDEXER_QUEUE_CAPACITY("cairo.column.indexer.queue.capacity"),
    CAIRO_VECTOR_AGGREGATE_QUEUE_CAPACITY("cairo.vector.aggregate.queue.capacity"),
    CAIRO_O3_CALLBACK_QUEUE_CAPACITY("cairo.o3.callback.queue.capacity"),
    CAIRO_O3_PARTITION_QUEUE_CAPACITY("cairo.o3.partition.queue.capacity"),
    CAIRO_O3_OPEN_COLUMN_QUEUE_CAPACITY("cairo.o3.open.column.queue.capacity"),
    CAIRO_O3_COPY_QUEUE_CAPACITY("cairo.o3.copy.queue.capacity"),
    CAIRO_O3_UPD_PARTITION_SIZE_QUEUE_CAPACITY("cairo.o3.upd.partition.size.queue.capacity"),
    CAIRO_O3_PURGE_DISCOVERY_QUEUE_CAPACITY("cairo.o3.purge.discovery.queue.capacity"),
    CAIRO_O3_COLUMN_MEMORY_SIZE("cairo.o3.column.memory.size"),
    CAIRO_MAX_UNCOMMITTED_ROWS("cairo.max.uncommitted.rows"),
    CAIRO_COMMIT_LAG("cairo.commit.lag"),
    CAIRO_O3_MAX_LAG("cairo.o3.max.lag"),
    CAIRO_O3_QUICKSORT_ENABLED("cairo.o3.quicksort.enabled"),
    CAIRO_RND_MEMORY_PAGE_SIZE("cairo.rnd.memory.page.size"),
    CAIRO_RND_MEMORY_MAX_PAGES("cairo.rnd.memory.max.pages"),
    CAIRO_REPLACE_BUFFER_MAX_SIZE("cairo.replace.buffer.max.size"),
    CAIRO_SQL_STR_FUNCTION_BUFFER_MAX_SIZE("cairo.sql.string.function.buffer.max.size"),
    CAIRO_SQL_ANALYTIC_STORE_PAGE_SIZE("cairo.sql.analytic.store.page.size"),
    CAIRO_SQL_ANALYTIC_STORE_MAX_PAGES("cairo.sql.analytic.store.max.pages"),
    CAIRO_SQL_ANALYTIC_ROWID_PAGE_SIZE("cairo.sql.analytic.rowid.page.size"),
    CAIRO_SQL_ANALYTIC_ROWID_MAX_PAGES("cairo.sql.analytic.rowid.max.pages"),
    CAIRO_SQL_ANALYTIC_TREE_PAGE_SIZE("cairo.sql.analytic.tree.page.size"),
    CAIRO_SQL_ANALYTIC_TREE_MAX_PAGES("cairo.sql.analytic.tree.max.pages"),
    CAIRO_O3_TXN_SCOREBOARD_ENTRY_COUNT("cairo.o3.txn.scoreboard.entry.count"),
    CAIRO_LATESTBY_QUEUE_CAPACITY("cairo.latestby.queue.capacity"),
    CAIRO_O3_PARTITION_PURGE_LIST_INITIAL_CAPACITY("cairo.o3.partition.purge.list.initial.capacity"),
    CAIRO_O3_ENABLED("cairo.o3.enabled"),
    CAIRO_QUERY_CACHE_EVENT_QUEUE_CAPACITY("cairo.query.cache.event.queue.capacity"),
    CAIRO_IO_URING_ENABLED("cairo.iouring.enabled"),
    CAIRO_MAX_CRASH_FILES("cairo.max.crash.files"),
    CIRCUIT_BREAKER_THROTTLE("circuit.breaker.throttle"),
    CIRCUIT_BREAKER_BUFFER_SIZE("circuit.breaker.buffer.size"),
    CONFIG_VALIDATION_STRICT("config.validation.strict"),
    HTTP_MIN_ENABLED("http.min.enabled"),
    HTTP_MIN_WORKER_AFFINITY("http.min.worker.affinity"),
    HTTP_MIN_WORKER_YIELD_THRESHOLD("http.min.worker.yield.threshold"),
    HTTP_MIN_WORKER_SLEEP_THRESHOLD("http.min.worker.sleep.threshold"),
    HTTP_MIN_WORKER_SLEEP_TIMEOUT("http.min.worker.sleep.timeout"),
    HTTP_MIN_BIND_TO("http.min.bind.to"),
    HTTP_MIN_NET_BIND_TO("http.min.net.bind.to"),
    HTTP_MIN_WORKER_HALT_ON_ERROR("http.min.worker.haltOnError"),
    HTTP_MIN_NET_IDLE_CONNECTION_TIMEOUT("http.min.net.idle.connection.timeout"),
    HTTP_MIN_NET_CONNECTION_TIMEOUT("http.min.net.connection.timeout"),
    HTTP_MIN_NET_QUEUED_CONNECTION_TIMEOUT("http.min.net.queued.connection.timeout"),
    HTTP_MIN_NET_CONNECTION_QUEUE_TIMEOUT("http.min.net.connection.queue.timeout"),
    HTTP_MIN_NET_SND_BUF_SIZE("http.min.net.snd.buf.size"),
    HTTP_MIN_NET_CONNECTION_SNDBUF("http.min.net.connection.sndbuf"),
    HTTP_NET_RCV_BUF_SIZE("http.net.rcv.buf.size"),
    HTTP_MIN_NET_CONNECTION_RCVBUF("http.min.net.connection.rcvbuf"),
    HTTP_MIN_NET_CONNECTION_HINT("http.min.net.connection.hint"),
    HTTP_ENABLED("http.enabled"),
    HTTP_MULTIPART_HEADER_BUFFER_SIZE("http.multipart.header.buffer.size"),
    HTTP_MULTIPART_IDLE_SPIN_COUNT("http.multipart.idle.spin.count"),
    HTTP_RECEIVE_BUFFER_SIZE("http.receive.buffer.size"),
    HTTP_REQUEST_HEADER_BUFFER_SIZE("http.request.header.buffer.size"),
    HTTP_WORKER_AFFINITY("http.worker.affinity"),
    HTTP_WORKER_HALT_ON_ERROR("http.worker.haltOnError"),
    HTTP_WORKER_YIELD_THRESHOLD("http.worker.yield.threshold"),
    HTTP_WORKER_SLEEP_THRESHOLD("http.worker.sleep.threshold"),
    HTTP_WORKER_SLEEP_TIMEOUT("http.worker.sleep.timeout"),
    HTTP_SEND_BUFFER_SIZE("http.send.buffer.size"),
    HTTP_STATIC_INDEX_FILE_NAME("http.static.index.file.name"),
    HTTP_FROZEN_CLOCK("http.frozen.clock"),
    HTTP_ALLOW_DEFLATE_BEFORE_SEND("http.allow.deflate.before.send"),
    HTTP_SERVER_KEEP_ALIVE("http.server.keep.alive"),
    HTTP_VERSION("http.version"),
    HTTP_STATIC_PUBLIC_DIRECTORY("http.static.public.directory"),
    HTTP_NET_CONNECTION_HINT("http.net.connection.hint"),
    HTTP_NET_IDLE_CONNECTION_TIMEOUT("http.net.idle.connection.timeout"),
    HTTP_NET_CONNECTION_TIMEOUT("http.net.connection.timeout"),
    HTTP_NET_QUEUED_CONNECTION_TIMEOUT("http.net.queued.connection.timeout"),
    HTTP_NET_CONNECTION_QUEUE_TIMEOUT("http.net.connection.queue.timeout"),
    HTTP_NET_SND_BUF_SIZE("http.net.snd.buf.size"),
    HTTP_NET_CONNECTION_SNDBUF("http.net.connection.sndbuf"),
    HTTP_NET_CONNECTION_RCVBUF("http.net.connection.rcvbuf"),
    HTTP_TEXT_JSON_CACHE_LIMIT("http.text.json.cache.limit"),
    HTTP_TEXT_JSON_CACHE_SIZE("http.text.json.cache.size"),
    HTTP_TEXT_MAX_REQUIRED_DELIMITER_STDDEV("http.text.max.required.delimiter.stddev"),
    HTTP_TEXT_MAX_REQUIRED_LINE_LENGTH_STDDEV("http.text.max.required.line.length.stddev"),
    HTTP_TEXT_ROLL_BUFFER_LIMIT("http.text.roll.buffer.limit"),
    HTTP_TEXT_ROLL_BUFFER_SIZE("http.text.roll.buffer.size"),
    HTTP_TEXT_UTF8_SINK_SIZE("http.text.utf8.sink.size"),
    HTTP_SECURITY_READONLY("http.security.readonly"),
    HTTP_SECURITY_MAX_RESPONSE_ROWS("http.security.max.response.rows"),
    HTTP_SECURITY_INTERRUPT_ON_CLOSED_CONNECTION("http.security.interrupt.on.closed.connection"),
    HTTP_BIND_TO("http.bind.to"),
    HTTP_BUSY_RETRY_MAXIMUM_WAIT_BEFORE_RETRY("http.busy.retry.maximum.wait.before.retry"),
    HTTP_BUSY_RETRY_EXPONENTIAL_WAIT_MULTIPLIER("http.busy.retry.exponential.wait.multiplier"),
    HTTP_BUSY_RETRY_INITIAL_WAIT_QUEUE_SIZE("http.busy.retry.initialWaitQueueSize"),
    HTTP_BUSY_RETRY_MAX_PROCESSING_QUEUE_SIZE("http.busy.retry.maxProcessingQueueSize"),
    HTTP_MIN_WORKER_COUNT("http.min.worker.count"),
    HTTP_MIN_NET_CONNECTION_LIMIT("http.min.net.connection.limit"),
    HTTP_NET_BIND_TO("http.net.bind.to"),
    HTTP_CONNECTION_POOL_INITIAL_CAPACITY("http.connection.pool.initial.capacity"),
    HTTP_CONNECTION_STRING_POOL_CAPACITY("http.connection.string.pool.capacity"),
    HTTP_WORKER_COUNT("http.worker.count"),
    HTTP_KEEP_ALIVE_TIMEOUT("http.keep-alive.timeout"),
    HTTP_KEEP_ALIVE_MAX("http.keep-alive.max"),
    HTTP_NET_ACTIVE_CONNECTION_LIMIT("http.net.active.connection.limit"),
    HTTP_NET_CONNECTION_LIMIT("http.net.connection.limit"),
    HTTP_TEXT_DATE_ADAPTER_POOL_CAPACITY("http.text.date.adapter.pool.capacity"),
    HTTP_TEXT_METADATA_STRING_POOL_CAPACITY("http.text.metadata.string.pool.capacity"),
    HTTP_TEXT_ANALYSIS_MAX_LINES("http.text.analysis.max.lines"),
    HTTP_TEXT_LEXER_STRING_POOL_CAPACITY("http.text.lexer.string.pool.capacity"),
    HTTP_TEXT_TIMESTAMP_ADAPTER_POOL_CAPACITY("http.text.timestamp.adapter.pool.capacity"),
    HTTP_JSON_QUERY_CONNECTION_CHECK_FREQUENCY("http.json.query.connection.check.frequency"),
    HTTP_JSON_QUERY_FLOAT_SCALE("http.json.query.float.scale"),
    HTTP_JSON_QUERY_DOUBLE_SCALE("http.json.query.double.scale"),
    HTTP_TEXT_ADAPTER_SET_CONFIG("http.text.adapter.set.config"),
    HTTP_QUERY_CACHE_ENABLED("http.query.cache.enabled"),
    HTTP_QUERY_CACHE_BLOCK_COUNT("http.query.cache.block.count"),
    HTTP_QUERY_CACHE_ROW_COUNT("http.query.cache.row.count"),
    LINE_UDP_BIND_TO("line.udp.bind.to"),
    LINE_UDP_HALT_ON_ERROR("line.udp.haltOnError"),
    LINE_UDP_JOIN("line.udp.join"),
    LINE_UDP_COMMIT_RATE("line.udp.commit.rate"),
    LINE_UDP_MSG_BUFFER_SIZE("line.udp.msg.buffer.size"),
    LINE_UDP_MSG_COUNT("line.udp.msg.count"),
    LINE_UDP_RECEIVE_BUFFER_SIZE("line.udp.receive.buffer.size"),
    LINE_UDP_ENABLED("line.udp.enabled"),
    LINE_UDP_OWN_THREAD_AFFINITY("line.udp.own.thread.affinity"),
    LINE_UDP_OWN_THREAD("line.udp.own.thread"),
    LINE_UDP_UNICAST("line.udp.unicast"),
    LINE_UDP_COMMIT_MODE("line.udp.commit.mode"),
    LINE_UDP_TIMESTAMP("line.udp.timestamp"),
    LINE_TCP_ENABLED("line.tcp.enabled"),
    LINE_TCP_NET_ACTIVE_CONNECTION_LIMIT("line.tcp.net.active.connection.limit"),
    LINE_TCP_NET_CONNECTION_LIMIT("line.tcp.net.connection.limit"),
    LINE_TCP_NET_CONNECTION_HINT("line.tcp.net.connection.hint"),
    LINE_TCP_NET_BIND_TO("line.tcp.net.bind.to"),
    LINE_TCP_NET_IDLE_TIMEOUT("line.tcp.net.idle.timeout"),
    LINE_TCP_NET_CONNECTION_TIMEOUT("line.tcp.net.connection.timeout"),
    LINE_TCP_NET_QUEUED_TIMEOUT("line.tcp.net.queued.timeout"),
    LINE_TCP_NET_CONNECTION_QUEUE_TIMEOUT("line.tcp.net.connection.queue.timeout"),
    LINE_TCP_NET_RECV_BUF_SIZE("line.tcp.net.recv.buf.size"),
    LINE_TCP_NET_CONNECTION_RCVBUF("line.tcp.net.connection.rcvbuf"),
    LINE_TCP_CONNECTION_POOL_CAPACITY("line.tcp.connection.pool.capacity"),
    LINE_TCP_TIMESTAMP("line.tcp.timestamp"),
    LINE_TCP_MSG_BUFFER_SIZE("line.tcp.msg.buffer.size"),
    LINE_TCP_MAX_MEASUREMENT_SIZE("line.tcp.max.measurement.size"),
    LINE_TCP_WRITER_QUEUE_CAPACITY("line.tcp.writer.queue.capacity"),
    LINE_TCP_WRITER_WORKER_COUNT("line.tcp.writer.worker.count"),
    LINE_TCP_WRITER_WORKER_AFFINITY("line.tcp.writer.worker.affinity"),
    LINE_TCP_WRITER_HALT_ON_ERROR("line.tcp.writer.halt.on.error"),
    LINE_TCP_WRITER_WORKER_YIELD_THRESHOLD("line.tcp.writer.worker.yield.threshold"),
    LINE_TCP_WRITER_WORKER_SLEEP_THRESHOLD("line.tcp.writer.worker.sleep.threshold"),
    LINE_TCP_SYMBOL_CACHE_WAIT_US_BEFORE_RELOAD("line.tcp.symbol.cache.wait.us.before.reload"),
    LINE_TCP_IO_WORKER_COUNT("line.tcp.io.worker.count"),
    LINE_TCP_IO_WORKER_AFFINITY("line.tcp.io.worker.affinity"),
    LINE_TCP_IO_HALT_ON_ERROR("line.tcp.io.halt.on.error"),
    LINE_TCP_IO_WORKER_YIELD_THRESHOLD("line.tcp.io.worker.yield.threshold"),
    LINE_TCP_IO_WORKER_SLEEP_THRESHOLD("line.tcp.io.worker.sleep.threshold"),
    LINE_TCP_MAINTENANCE_JOB_INTERVAL("line.tcp.maintenance.job.interval"),
    LINE_TCP_COMMIT_INTERVAL_FRACTION("line.tcp.commit.interval.fraction"),
    LINE_TCP_COMMIT_INTERVAL_DEFAULT("line.tcp.commit.interval.default"),
    LINE_TCP_AUTH_DB_PATH("line.tcp.auth.db.path"),
    LINE_TCP_DEFAULT_PARTITION_BY("line.tcp.default.partition.by"),
    LINE_DEFAULT_PARTITION_BY("line.default.partition.by"),
    LINE_TCP_MIN_IDLE_MS_BEFORE_WRITER_RELEASE("line.tcp.min.idle.ms.before.writer.release"),
    LINE_TCP_DISCONNECT_ON_ERROR("line.tcp.disconnect.on.error"),
    LINE_TCP_UNDOCUMENTED_STRING_TO_CHAR_CAST_ALLOWED("line.tcp.undocumented.string.to.char.cast.allowed"),
    LINE_TCP_UNDOCUMENTED_SYMBOL_AS_FIELD_SUPPORTED("line.tcp.undocumented.symbol.as.field.supported"),
    LINE_TCP_UNDOCUMENTED_STRING_AS_TAG_SUPPORTED("line.tcp.undocumented.string.as.tag.supported"),
    LINE_FLOAT_DEFAULT_COLUMN_TYPE("line.float.default.column.type"),
    LINE_INTEGER_DEFAULT_COLUMN_TYPE("line.integer.default.column.type"),
    LINE_TCP_NET_IO_QUEUE_CAPACITY("line.tcp.net.io.queue.capacity"),
    LINE_TCP_IO_AGGRESSIVE_RECV("line.tcp.io.aggressive.recv"),
    METRICS_ENABLED("metrics.enabled"),
    NET_TEST_CONNECTION_BUFFER_SIZE("net.test.connection.buffer.size"),
    PG_ENABLED("pg.enabled"),
    PG_NET_CONNECTION_HINT("pg.net.connection.hint"),
    PG_NET_BIND_TO("pg.net.bind.to"),
    PG_NET_IDLE_TIMEOUT("pg.net.idle.timeout"),
    PG_NET_CONNECTION_TIMEOUT("pg.net.connection.timeout"),
    PG_NET_CONNECTION_QUEUE_TIMEOUT("pg.net.connection.queue.timeout"),
    PG_NET_RECV_BUF_SIZE("pg.net.recv.buf.size"),
    PG_NET_CONNECTION_RCVBUF("pg.net.connection.rcvbuf"),
    PG_NET_SEND_BUF_SIZE("pg.net.send.buf.size"),
    PG_PASSWORD("pg.password"),
    PG_USER("pg.user"),
    PG_RO_PASSWORD("pg.readonly.password"),
    PG_RO_USER("pg.readonly.user"),
    PG_RO_USER_ENABLED("pg.readonly.user.enabled"),
    PG_SECURITY_READONLY("pg.security.readonly"),
    PG_MAX_BLOB_SIZE_ON_QUERY("pg.max.blob.size.on.query"),
    PG_RECV_BUFFER_SIZE("pg.recv.buffer.size"),
    PG_SEND_BUFFER_SIZE("pg.send.buffer.size"),
    PG_DATE_LOCALE("pg.date.locale"),
    PG_WORKER_AFFINITY("pg.worker.affinity"),
    PG_HALT_ON_ERROR("pg.halt.on.error"),
    PG_WORKER_YIELD_THRESHOLD("pg.worker.yield.threshold"),
    PG_WORKER_SLEEP_THRESHOLD("pg.worker.sleep.threshold"),
    PG_DAEMON_POOL("pg.daemon.pool"),
    PG_SELECT_CACHE_ENABLED("pg.select.cache.enabled"),
    PG_NET_ACTIVE_CONNECTION_LIMIT("pg.net.active.connection.limit"),
    PG_NET_CONNECTION_LIMIT("pg.net.connection.limit"),
    PG_CHARACTER_STORE_CAPACITY("pg.character.store.capacity"),
    PG_BINARY_PARAM_COUNT_CAPACITY("pg.binary.param.count.capacity"),
    PG_CHARACTER_STORE_POOL_CAPACITY("pg.character.store.pool.capacity"),
    PG_CONNECTION_POOL_CAPACITY("pg.connection.pool.capacity"),
    PG_WORKER_COUNT("pg.worker.count"),
    PG_SELECT_CACHE_BLOCK_COUNT("pg.select.cache.block.count"),
    PG_SELECT_CACHE_ROW_COUNT("pg.select.cache.row.count"),
    PG_INSERT_CACHE_ENABLED("pg.insert.cache.enabled"),
    PG_INSERT_CACHE_BLOCK_COUNT("pg.insert.cache.block.count"),
    PG_INSERT_CACHE_ROW_COUNT("pg.insert.cache.row.count"),
    PG_INSERT_POOL_CAPACITY("pg.insert.pool.capacity"),
    PG_NAMED_STATEMENT_CACHE_CAPACITY("pg.named.statement.cache.capacity"),
    PG_NAMED_STATEMENT_POOL_CAPACITY("pg.named.statement.pool.capacity"),
    PG_PENDING_WRITERS_CACHE_CAPACITY("pg.pending.writers.cache.capacity"),
    PG_NET_CONNECTION_SNDBUF("pg.net.connection.sndbuf"),
    QUERY_TIMEOUT_SEC("query.timeout.sec"),
    SHARED_WORKER_COUNT("shared.worker.count"),
    SHARED_WORKER_AFFINITY("shared.worker.affinity"),
    SHARED_WORKER_HALT_ON_ERROR("shared.worker.haltOnError"),
    SHARED_WORKER_SLEEP_THRESHOLD("shared.worker.sleep.threshold"),
    SHARED_WORKER_SLEEP_TIMEOUT("shared.worker.sleep.timeout"),
    SHARED_WORKER_YIELD_THRESHOLD("shared.worker.yield.threshold"),
    TELEMETRY_ENABLED("telemetry.enabled"),
    TELEMETRY_DISABLE_COMPLETELY("telemetry.disable.completely"),
    TELEMETRY_QUEUE_CAPACITY("telemetry.queue.capacity"),
    TELEMETRY_HIDE_TABLES("telemetry.hide.tables"),
    PG_UPDATE_CACHE_ENABLED("pg.update.cache.enabled"),
    PG_UPDATE_CACHE_BLOCK_COUNT("pg.update.cache.block.count"),
    PG_UPDATE_CACHE_ROW_COUNT("pg.update.cache.row.count"),
    CAIRO_SQL_COLUMN_PURGE_QUEUE_CAPACITY("cairo.sql.column.purge.queue.capacity"),
    CAIRO_SQL_COLUMN_PURGE_TASK_POOL_CAPACITY("cairo.sql.column.purge.task.pool.capacity"),
    CAIRO_SQL_COLUMN_PURGE_RETRY_DELAY_LIMIT("cairo.sql.column.purge.retry.delay.limit"),
    CAIRO_SQL_COLUMN_PURGE_RETRY_DELAY("cairo.sql.column.purge.retry.delay"),
    CAIRO_SQL_COLUMN_PURGE_RETRY_DELAY_MULTIPLIER("cairo.sql.column.purge.retry.delay.multiplier"),
    CAIRO_SQL_SYSTEM_TABLE_PREFIX("cairo.system.table.prefix"),
    CAIRO_MAX_FILE_NAME_LENGTH("cairo.max.file.name.length"),
    LINE_AUTO_CREATE_NEW_COLUMNS("line.auto.create.new.columns"),
    LINE_AUTO_CREATE_NEW_TABLES("line.auto.create.new.tables"),
    CAIRO_SIMULATE_CRASH_ENABLED("cairo.enable.crash.simulation"),
    CAIRO_WAL_ENABLED_DEFAULT("cairo.wal.enabled.default"),
    CAIRO_WAL_PURGE_INTERVAL("cairo.wal.purge.interval"),
    CAIRO_WAL_SEGMENT_ROLLOVER_ROW_COUNT("cairo.wal.segment.rollover.row.count"),
    WAL_APPLY_WORKER_COUNT("wal.apply.worker.count"),
    WAL_APPLY_WORKER_AFFINITY("wal.apply.worker.affinity"),
    WAL_APPLY_WORKER_HALT_ON_ERROR("wal.apply.worker.haltOnError"),
    WAL_APPLY_WORKER_SLEEP_THRESHOLD("wal.apply.worker.sleep.threshold"),
    WAL_APPLY_WORKER_SLEEP_TIMEOUT("wal.apply.worker.sleep.timeout"),
    WAL_APPLY_WORKER_YIELD_THRESHOLD("wal.apply.worker.yield.threshold"),
    CAIRO_WAL_TXN_NOTIFICATION_QUEUE_CAPACITY("cairo.wal.txn.notification.queue.capacity"),
    CAIRO_WAL_SUPPORTED("cairo.wal.supported"),
    TABLE_TYPE_CONVERSION_ENABLED("table.type.conversion.enabled"),
    CAIRO_WAL_RECREATE_DISTRESSED_SEQUENCER_ATTEMPTS("cairo.wal.recreate.distressed.sequencer.attempts"),
    CAIRO_INACTIVE_WAL_WRITER_TTL("cairo.wal.inactive.writer.ttl"),
    READ_ONLY_INSTANCE("readonly"),
    CAIRO_TABLE_REGISTRY_AUTO_RELOAD_FREQUENCY("cairo.table.registry.auto.reload.frequency"),
    CAIRO_WAL_COMMIT_SQUASH_ROW_LIMIT("cairo.wal.commit.squash.row.limit");

    private static final Map<String, PropertyKey> nameMapping;
    private final String propertyPath;

    PropertyKey(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public static Optional<PropertyKey> getByString(String name) {
        return Optional.ofNullable(nameMapping.get(name));
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    static {
        nameMapping = Arrays.stream(PropertyKey.values()).collect(Collectors.toMap(PropertyKey::getPropertyPath, k -> k));
    }

}
