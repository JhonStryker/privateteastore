package com.priavteTeaStore.domain;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public enum TeaStoreStandard {
    MIN(1, "mini"),
    BIAO_ZHUN(2, "标准");

    int value;
    String name;

    TeaStoreStandard(int value, String name) {
        this.value = value;
        this.name = name;
    }


    public static TeaStoreStandard getStandard(int value) {
        for (TeaStoreStandard storeStandard : TeaStoreStandard.values()) {
            if (storeStandard.value == value) {
                return storeStandard;
            }
        }
        return null;
    }

}
