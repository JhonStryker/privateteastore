package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestMsg;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9
 */
public class CheckCheckCodeMsg extends ClientRequestMsg {
    @NotNull
    String phoneNum;

    @NotNull
    String checkCode;

    public CheckCheckCodeMsg() {
    }

    public CheckCheckCodeMsg(String phoneNum, String checkCode) {
        this.phoneNum = phoneNum;
        this.checkCode = checkCode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getPhoneNum() {

        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    @Override
    public CheckType getCheckType() {
        return CheckType.check_right_for_md5_token;
    }

    @Override
    protected boolean checkMsgContent() {
        return phoneNum.length() == 11 && checkCode.length() == 6;
    }
}
