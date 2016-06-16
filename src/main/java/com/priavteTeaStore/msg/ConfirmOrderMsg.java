package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestMsg;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class ConfirmOrderMsg extends ClientRequestMsg {
    @NotNull
    Long orderId;

    public ConfirmOrderMsg(Long orderId) {
        this.orderId = orderId;
    }

    public ConfirmOrderMsg() {
    }

    public Long getOrderId() {
        return orderId;
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
