package org.apache.ignite.smart.plugins;

import org.apache.ignite.plugin.PluginConfiguration;

public class MyNodeValidationPluginConfiguration implements PluginConfiguration {

    private String token;

    public MyNodeValidationPluginConfiguration(final String token)
    {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
