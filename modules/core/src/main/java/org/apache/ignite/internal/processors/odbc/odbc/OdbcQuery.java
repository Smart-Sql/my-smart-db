/*
 * Copyright 2019 GridGain Systems, Inc. and Contributors.
 *
 * Licensed under the GridGain Community Edition License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gridgain.com/products/software/community-edition/gridgain-community-edition-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.odbc.odbc;

import org.apache.ignite.internal.binary.BinaryReaderExImpl;
import org.apache.ignite.internal.processors.odbc.SqlListenerUtils;
import org.apache.ignite.internal.util.typedef.internal.S;

/**
 * ODBC SQL query with parameters.
 */
public class OdbcQuery {
    /** Query SQL. */
    private String sql;

    /** Arguments. */
    private Object[] args;

    /**
     * Default constructor is used for serialization.
     */
    public OdbcQuery() {
        // No-op.
    }

    /**
     * @return Query SQL string.
     */
    public String sql() {
        return sql;
    }

    /**
     * @return Query arguments.
     */
    public Object[] args() {
        return args;
    }

    /**
     * Reads fields from provided reader.
     *
     * @param reader Binary object reader.
     */
    public void readBinary(BinaryReaderExImpl reader) {
        sql = reader.readString();

        int argsNum = reader.readInt();

        args = new Object[argsNum];

        for (int i = 0; i < argsNum; ++i)
            args[i] = SqlListenerUtils.readObject(reader, false);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(OdbcQuery.class, this);
    }
}
