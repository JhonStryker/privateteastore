package com.priavteTeaStore.msg;


import com.priavteTeaStore.msg.base.ClientRequestMsg;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class ActiveStoreCardMsg extends ClientRequestMsg {

    Long cardId;

    public ActiveStoreCardMsg() {
    }

    @Override
    public CheckType getCheckType() {
        return CheckType.check_right_for_user_info;
    }

    @Override
    protected boolean checkMsgContent() {
        return true;
    }

    public Long getCardId() {
        return cardId;
    }
}
