package org.apache.ignite.smart.plugins;


import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.plugin.*;

import java.io.Serializable;
import java.util.UUID;

public class MyPluginNodeValidationProvider implements PluginProvider<MyNodeValidationPluginConfiguration> {

    private MyNodeValidationPluginConfiguration pluginConfiguration;
    private MyDiscoveryData remoteDiscoveryData;
    private String token;

    private MyPluginNodeValidation plugin;

    @Override
    public String name() {
        return "SmartSqlPlugin";
    }

    @Override
    public String version() {
        return "0.1.0-SNAPSHOT";
    }

    @Override
    public String copyright() {
        return "陈飞";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public MyPluginNodeValidation plugin() {
        return plugin;
    }

    @Override
    public void initExtensions(PluginContext ctx, ExtensionRegistry registry) throws IgniteCheckedException {
        plugin = new MyPluginNodeValidation(token, ctx);

        IgniteConfiguration igniteCfg = ctx.igniteConfiguration();

        if (igniteCfg.getPluginProviders() != null) {
            for (PluginProvider pluginCfg : igniteCfg.getPluginProviders()) {
                if (pluginCfg instanceof MyPluginNodeValidationProvider) {
                    pluginConfiguration = new MyNodeValidationPluginConfiguration(((MyPluginNodeValidationProvider) pluginCfg).getToken());
                    System.out.println("插件初始化完成：" + pluginConfiguration.getToken());
                }
            }
        }
    }

    @Override
    public <T> T createComponent(PluginContext ctx, Class<T> cls) {
        return null;
    }

    @Override
    public CachePluginProvider createCacheProvider(CachePluginContext ctx) {
        return null;
    }

    @Override
    public void start(PluginContext ctx) throws IgniteCheckedException {

    }

    @Override
    public void stop(boolean cancel) throws IgniteCheckedException {

    }

    @Override
    public void onIgniteStart() throws IgniteCheckedException {

    }

    @Override
    public void onIgniteStop(boolean cancel) {

    }

    @Override
    public Serializable provideDiscoveryData(UUID nodeId) {
        MyDiscoveryData myDiscoveryData = new MyDiscoveryData(pluginConfiguration.getToken());
        return myDiscoveryData;
    }

    @Override
    public void receiveDiscoveryData(UUID nodeId, Serializable data) {
        //remoteDiscoveryData = data instanceof MyDiscoveryData ? (MyDiscoveryData)data : null;
    }

    @Override
    public void validateNewNode(ClusterNode node) throws PluginValidationException {

    }

    @Override
    public void validateNewNode(ClusterNode node, Serializable data) {
        remoteDiscoveryData = data instanceof MyDiscoveryData ? (MyDiscoveryData)data : null;
        if (remoteDiscoveryData == null || !remoteDiscoveryData.getToken().equals(pluginConfiguration.getToken())) {
            String msg = remoteDiscoveryData == null ? "no token provided" : "bad token provided: " + remoteDiscoveryData.getToken();

            throw new PluginValidationException(msg, msg, node.id());
        }
        validateNewNode(node);
    }
}
