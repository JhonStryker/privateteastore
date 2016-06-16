package com.priavteTeaStore.msg;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class AddShopMsg {
    @NotNull
    private String shopName;

    public AddShopMsg() {
    }

    public AddShopMsg(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }
}
