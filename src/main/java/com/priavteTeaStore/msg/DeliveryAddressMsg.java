package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestMsg;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class DeliveryAddressMsg extends ClientRequestMsg {
    @NotNull
    String name;

    @NotNull
    String phoneNum;

    @NotNull
    String addressInfo;

    @NotNull
    Long userId;

    public DeliveryAddressMsg() {
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public CheckType getCheckType() {
        return CheckType.check_right_for_user_info;
    }

    @Override
    protected boolean checkMsgContent() {
        return true;
    }
}
