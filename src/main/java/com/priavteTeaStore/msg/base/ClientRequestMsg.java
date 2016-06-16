package com.priavteTeaStore.msg.base;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.priavteTeaStore.common.validate.AccessValid;
import com.priavteTeaStore.domain.AccessInfo;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.util.ERR;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@AccessValid
public abstract class ClientRequestMsg {
    private final static Logger logger = LoggerFactory.getLogger(ClientRequestMsg.class);
    protected String concreteErrorInfo = "";
    @NotNull
    AccessInfo accessInfo;

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    abstract public CheckType getCheckType();

    protected abstract boolean checkMsgContent();

    public void checkMsg() {
        logger.info(toString());//设置日志级别可以看到request信息

        if (!checkMsgContent()) {
            if (concreteErrorInfo.equals("")) {
                throw new ToClientException(ERR.req_msg_error);
            }
            throw new ToClientException(ERR.req_msg_error, concreteErrorInfo);
        }
    }

    public enum CheckType {
        check_right_for_md5_token,
        check_right_for_access_token,
        check_right_for_user_info
    }
}
