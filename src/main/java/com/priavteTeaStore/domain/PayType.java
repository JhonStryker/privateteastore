package com.priavteTeaStore.domain;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public enum PayType {
    wei_xin("微信支付", 0),
    yin_lian("银联支付", 1),
    ali("支付宝支付", 2);

    int value;
    String content;

    PayType(String content, int value) {
        this.content = content;
        this.value = value;
    }

    public static PayType get(Integer payType) {
        for (PayType item : PayType.values()) {
            if (item.value == payType.intValue()) {
                return item;
            }
        }
        return null;
    }
}
