package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestMsg;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class RegisterMsg extends ClientRequestMsg {

    private String md5_key;
    private String check_code;
    private String password;//注意，这里提供密文的password
    private String phone_num;

    public RegisterMsg(String md5_key, String check_code, String password, String phone_num) {
        this.md5_key = md5_key;
        this.check_code = check_code;
        this.password = password;
        this.phone_num = phone_num;
    }

    public RegisterMsg() {
    }

    public String getMd5_key() {
        return md5_key;
    }

    public String getCheck_code() {
        return check_code;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    @Override
    public String toString() {
        return "RegisterMsg{" +
                "md5_key='" + md5_key + '\'' +
                ", check_code='" + check_code + '\'' +
                ", password='" + password + '\'' +
                ", phone_num='" + phone_num + '\'' +
                '}';
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
