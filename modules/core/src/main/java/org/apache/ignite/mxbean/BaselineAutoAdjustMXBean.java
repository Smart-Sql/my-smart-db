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

package org.apache.ignite.mxbean;

import org.apache.ignite.internal.cluster.DistributedBaselineConfiguration;

/**
 * This interface defines JMX view on {@link DistributedBaselineConfiguration}.
 */
public interface BaselineAutoAdjustMXBean {
    /** */
    @MXBeanDescription("Whether baseline autoadjustment is enabled ot not.")
    boolean isAutoAdjustmentEnabled();

    /** */
    @MXBeanDescription("Baseline autoadjustment timeout value.")
    long getAutoAdjustmentTimeout();

    /** */
    @MXBeanDescription("Time until baseline will be adjusted automatically.")
    long getTimeUntilAutoAdjust();

    /** */
    @MXBeanDescription("State of task of auto-adjust(IN_PROGRESS, SCHEDULED, NOT_SCHEDULED).")
    String getTaskState();

    /** */
    @MXBeanDescription("Enable/disable baseline autoadjustment feature.")
    public void setAutoAdjustmentEnabled(
        @MXBeanParameter(name = "enabled", description = "Enable/disable flag.") boolean enabled
    );

    /** */
    @MXBeanDescription("Set baseline autoadjustment timeout value.")
    public void setAutoAdjustmentTimeout(
        @MXBeanParameter(name = "timeout", description = "Timeout value.") long timeout
    );
}
