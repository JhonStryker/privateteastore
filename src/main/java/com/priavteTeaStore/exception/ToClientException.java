package com.priavteTeaStore.exception;

import com.priavteTeaStore.util.ERR;

/**
 * Created by Thoughtworks on 16/6/9.
 */

/**
 * 对客户的异常
 */
public class ToClientException extends RuntimeException {
    public ToClientException(ERR value) {
        super(value.getContent());
    }

    public ToClientException(ERR value, String concreteErrorInfo) {
        super(value.getContent() + ":" + concreteErrorInfo);
    }
}
