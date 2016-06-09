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

package com.questdb.ql.model;

import com.questdb.std.ObjList;
import com.questdb.std.ObjectFactory;

public final class AnalyticColumn extends QueryColumn {
    public final static ObjectFactory<AnalyticColumn> FACTORY = new ObjectFactory<AnalyticColumn>() {
        @Override
        public AnalyticColumn newInstance() {
            return new AnalyticColumn();
        }
    };
    private final ObjList<ExprNode> partitionBy = new ObjList<>(2);
    private final ObjList<ExprNode> orderBy = new ObjList<>(2);

    private AnalyticColumn() {
    }

    @Override
    public void clear() {
        super.clear();
        partitionBy.clear();
        orderBy.clear();
    }

    @Override
    public AnalyticColumn of(String alias, ExprNode ast) {
        return (AnalyticColumn) super.of(alias, ast);
    }

    public ObjList<ExprNode> getOrderBy() {
        return orderBy;
    }

    public ObjList<ExprNode> getPartitionBy() {
        return partitionBy;
    }
}
