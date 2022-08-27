package org.apache.ignite.smart.plugins;

import java.io.Serializable;

public class MyDiscoveryData implements Serializable {
    private String token;

    public MyDiscoveryData(final String token)
    {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "MyDiscoveryData{" +
                "token='" + token + '\'' +
                '}';
    }
}
