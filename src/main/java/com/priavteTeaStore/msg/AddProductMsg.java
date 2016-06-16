package com.priavteTeaStore.msg;


import com.priavteTeaStore.domain.ClientTeaStoreInfo;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class AddProductMsg {
    Long categoryId;//类别id
    Long shopId;


    ClientTeaStoreInfo clientTeaStoreInfo;

    public AddProductMsg() {
    }

    public AddProductMsg(Long categoryId, Long shopId, ClientTeaStoreInfo clientTeaStoreInfo) {
        this.categoryId = categoryId;
        this.shopId = shopId;
        this.clientTeaStoreInfo = clientTeaStoreInfo;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public ClientTeaStoreInfo getClientTeaStoreInfo() {
        return clientTeaStoreInfo;
    }
}
