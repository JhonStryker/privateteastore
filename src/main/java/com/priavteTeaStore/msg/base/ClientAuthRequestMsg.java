package com.priavteTeaStore.msg.base;

import com.priavteTeaStore.domain.AccessInfo;

import javax.validation.constraints.NotNull;


/**
 * Created by Thoughtworks on 16/6/11.
 */

//需要鉴权的消息继承该基类
public abstract class ClientAuthRequestMsg extends ClientRequestMsg {

    @NotNull
    AccessInfo accessInfo;

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    @Override
    public String toString() {
        return "ClientAuthRequestMsg{" +
                "accessInfo=" + accessInfo +
                "} " + super.toString();
    }
}
