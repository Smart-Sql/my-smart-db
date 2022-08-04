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

package org.apache.ignite.internal.binary;

import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.binary.BinaryObjectException;

/**
 * Abstract identity resolver with common routines.
 */
public abstract class BinaryAbstractIdentityResolver implements BinaryIdentityResolver {
    /** {@inheritDoc} */
    @Override public int hashCode(BinaryObject obj) {
        if (obj == null)
            throw new BinaryObjectException("Cannot calculate hash code because binary object is null.");

        return hashCode0(obj);
    }

    /** {@inheritDoc} */
    @Override public boolean equals(BinaryObject o1, BinaryObject o2) {
        return o1 == o2 || (o1 != null && o2 != null && equals0(o1, o2));
    }

    /**
     * Internal hash code routine.
     *
     * @param obj Object.
     * @return Result.
     */
    protected abstract int hashCode0(BinaryObject obj);

    /**
     * Internal equals routine.
     *
     * @param o1 First object.
     * @param o2 Second object.
     * @return Result.
     */
    protected abstract boolean equals0(BinaryObject o1, BinaryObject o2);
}