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

package org.apache.ignite.internal.processors.service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import org.apache.ignite.IgniteException;
import org.apache.ignite.internal.GridKernalContext;
import org.apache.ignite.internal.managers.deployment.GridDeployment;
import org.apache.ignite.internal.util.tostring.GridToStringInclude;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.apache.ignite.lang.IgniteBiTuple;
import org.apache.ignite.lang.IgniteUuid;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceConfiguration;
import org.apache.ignite.services.ServiceDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Service's information container.
 */
public class ServiceInfo implements ServiceDescriptor {
    /** */
    private static final long serialVersionUID = 0L;

    /** Context. */
    private transient volatile GridKernalContext ctx;

    /** Origin node ID. */
    private final UUID originNodeId;

    /** Service id. */
    private final IgniteUuid srvcId;

    /** Service configuration. */
    private final ServiceConfiguration cfg;

    /** Statically configured flag. */
    private final boolean staticCfg;

    /** Topology snapshot. */
    @GridToStringInclude
    private volatile Map<UUID, Integer> top = Collections.emptyMap();

    /** Service class. */
    private transient volatile Class<? extends Service> srvcCls;

    /**
     * @param originNodeId Initiating node id.
     * @param srvcId Service id.
     * @param cfg Service configuration.
     */
    public ServiceInfo(@NotNull UUID originNodeId, @NotNull IgniteUuid srvcId, @NotNull ServiceConfiguration cfg) {
        this(originNodeId, srvcId, cfg, false);
    }

    /**
     * @param originNodeId Initiating node id.
     * @param srvcId Service id.
     * @param cfg Service configuration.
     * @param staticCfg Statically configured flag.
     */
    public ServiceInfo(@NotNull UUID originNodeId, @NotNull IgniteUuid srvcId, @NotNull ServiceConfiguration cfg,
        boolean staticCfg) {
        this.originNodeId = originNodeId;
        this.srvcId = srvcId;
        this.cfg = cfg;
        this.staticCfg = staticCfg;
    }

    /**
     * Sets kernal context.
     *
     * @param ctx Context.
     */
    public void context(GridKernalContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Sets service's new topology snapshot.
     *
     * @param top Topology snapshot.
     */
    public void topologySnapshot(@NotNull Map<UUID, Integer> top) {
        this.top = top;
    }

    /**
     * Returns service's configuration.
     *
     * @return Service configuration.
     */
    public ServiceConfiguration configuration() {
        return cfg;
    }

    /**
     * @return {@code true} if statically configured.
     */
    public boolean staticallyConfigured() {
        return staticCfg;
    }

    /**
     * Rerurns services id.
     *
     * @return Service id.
     */
    public IgniteUuid serviceId() {
        return srvcId;
    }

    /** {@inheritDoc} */
    @Override public String name() {
        return cfg.getName();
    }

    /** {@inheritDoc} */
    @Override public Class<? extends Service> serviceClass() {
        if (cfg instanceof LazyServiceConfiguration) {
            if (srvcCls != null)
                return srvcCls;

            String clsName = ((LazyServiceConfiguration)cfg).serviceClassName();

            try {
                srvcCls = (Class<? extends Service>)Class.forName(clsName);

                return srvcCls;
            }
            catch (ClassNotFoundException e) {
                if (ctx != null) {
                    GridDeployment srvcDep = ctx.deploy().getDeployment(clsName);

                    if (srvcDep != null) {
                        IgniteBiTuple<Class<?>, Throwable> classOrError = srvcDep.deployedClass(clsName);

                        srvcCls = (Class<? extends Service>) classOrError.get1();

                        if (srvcCls != null)
                            return srvcCls;

                        Throwable err = classOrError.get2();

                        if (err != null)
                            e.addSuppressed(err);
                    }
                }

                throw new IgniteException("Failed to find service class: " + clsName, e);
            }
        }
        else
            return cfg.getService().getClass();
    }

    /** {@inheritDoc} */
    @Override public int totalCount() {
        return cfg.getTotalCount();
    }

    /** {@inheritDoc} */
    @Override public int maxPerNodeCount() {
        return cfg.getMaxPerNodeCount();
    }

    /** {@inheritDoc} */
    @Nullable @Override public String cacheName() {
        return cfg.getCacheName();
    }

    /** {@inheritDoc} */
    @Nullable @Override public <K> K affinityKey() {
        return (K)cfg.getAffinityKey();
    }

    /** {@inheritDoc} */
    @Override public UUID originNodeId() {
        return originNodeId;
    }

    /** {@inheritDoc} */
    @Override public Map<UUID, Integer> topologySnapshot() {
        return Collections.unmodifiableMap(top);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(ServiceInfo.class, this);
    }
}
