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

package org.apache.ignite.internal.processors.schedule;

import java.util.concurrent.Callable;
import org.apache.ignite.IgniteException;
import org.apache.ignite.internal.GridKernalContext;
import org.apache.ignite.scheduler.SchedulerFuture;

/**
 * No-op implementation of {@link IgniteScheduleProcessorAdapter}, throws exception on usage attempt.
 */
public class IgniteNoopScheduleProcessor extends IgniteScheduleProcessorAdapter {
    /**
     * @param ctx Kernal context.
     */
    public IgniteNoopScheduleProcessor(GridKernalContext ctx) {
        super(ctx);
    }

    /** {@inheritDoc} */
    @Override public SchedulerFuture<?> schedule(final String name, Runnable c, String pattern) {
        throw processorException();
    }

    /** {@inheritDoc} */
    @Override public <R> SchedulerFuture<R> schedule(final String name, Callable<R> c, String pattern) {
        throw processorException();
    }

    /**
     * @return No-op processor usage exception;
     */
    private IgniteException processorException() {
        return new IgniteException("Current Ignite configuration does not support schedule functionality " +
            "(consider adding ignite-schedule module to classpath).");
    }
}