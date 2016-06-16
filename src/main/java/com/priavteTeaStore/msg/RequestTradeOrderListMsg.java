package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestListMsg;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class RequestTradeOrderListMsg extends ClientRequestListMsg {

    public RequestTradeOrderListMsg() {
    }

    public RequestTradeOrderListMsg(Integer size, Integer pageNo) {
        super(size, pageNo);
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
