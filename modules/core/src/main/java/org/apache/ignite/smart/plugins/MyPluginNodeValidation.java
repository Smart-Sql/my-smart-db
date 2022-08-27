package org.apache.ignite.smart.plugins;

import org.apache.ignite.plugin.IgnitePlugin;
import org.apache.ignite.plugin.PluginContext;

public class MyPluginNodeValidation implements IgnitePlugin {

    private String token;
    private PluginContext context;

    public MyPluginNodeValidation(String token, PluginContext context)
    {
        this.token = token;
        this.context = context;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PluginContext getContext() {
        return context;
    }

    public void setContext(PluginContext context) {
        this.context = context;
    }
}
