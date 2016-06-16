package com.priavteTeaStore.domain;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class AccessToken {
    private String access_token;
    private String access_token_secret;

    public AccessToken() {
    }

    public AccessToken(String access_token, String access_token_secret) {
        this.access_token = access_token;
        this.access_token_secret = access_token_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token_secret() {
        return access_token_secret;
    }

    public void setAccess_token_secret(String access_token_secret) {
        this.access_token_secret = access_token_secret;
    }

}
