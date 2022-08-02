package cn.mysuper.model;

import java.io.Serializable;

public class MyUrlToken implements Serializable {
    private static final long serialVersionUID = 7939131112148132767L;
    private String userToken;
    private String url;

    public MyUrlToken(String userToken, String url) {
        this.url = url;
        this.userToken = userToken;
    }

    public MyUrlToken() {
    }

    public String getUserToken() {
        return this.userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}