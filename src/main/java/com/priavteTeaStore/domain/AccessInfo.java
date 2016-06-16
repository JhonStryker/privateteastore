package com.priavteTeaStore.domain;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class AccessInfo {

    private String app_key;
    private String signature;
    private String access_token;
    private String phone_num;

    public AccessInfo() {
    }

    public AccessInfo(String app_key, String signature, String access_token, String phone_num) {
        this.app_key = app_key;
        this.signature = signature;
        this.access_token = access_token;
        this.phone_num = phone_num;
    }

    public String getApp_key() {
        return app_key;
    }

    public String getSignature() {
        return signature;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getPhone_num() {
        return phone_num;
    }

    boolean checkAccess() {
        return true;
    }
}
