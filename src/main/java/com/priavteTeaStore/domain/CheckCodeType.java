package com.priavteTeaStore.domain;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public enum CheckCodeType {
    Register(1, "注册"),;

    private final int value;
    private final String desc;

    private CheckCodeType(int v, String title) {
        value = v;
        desc = title;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}