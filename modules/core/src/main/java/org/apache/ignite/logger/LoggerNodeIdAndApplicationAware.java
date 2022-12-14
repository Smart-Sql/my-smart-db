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

package org.apache.ignite.logger;

import java.util.UUID;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for Ignite file appenders to attach postfix to log file names.
 */
public interface LoggerNodeIdAndApplicationAware extends LoggerNodeIdAware {
    /** {@inheritDoc} */
    @Override public default void setNodeId(UUID nodeId) {
        setApplicationAndNode(null, nodeId);
    }

    /**
     * Sets application name and node ID.
     *
     * @param application Application.
     * @param nodeId Node ID.
     */
    public void setApplicationAndNode(@Nullable String application, UUID nodeId);
}
