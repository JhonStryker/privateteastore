package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestListMsg;

/**
 * Created by Thoughtworks on 16/6/14.
 */
public class ListProductMsg extends ClientRequestListMsg {

    @Override
    public CheckType getCheckType() {
        return CheckType.check_right_for_md5_token;
    }

    @Override
    protected boolean checkMsgContent() {
        return true;
    }
}
