package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestMsg;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class LoginMsg extends ClientRequestMsg {
    @NotNull
    String userName;

    @NotNull
    String app_key;

    public LoginMsg(String userName, String app_key) {
        this.userName = userName;
        this.app_key = app_key;
    }

    public LoginMsg() {
    }

    public String getUserName() {
        return userName;
    }

    public String getApp_key() {
        return app_key;
    }

    @Override
    public CheckType getCheckType() {
        return CheckType.check_right_for_md5_token;
    }

    @Override
    protected boolean checkMsgContent() {
        return true;
    }
}
