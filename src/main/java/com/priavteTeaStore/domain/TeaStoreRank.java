package com.priavteTeaStore.domain;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public enum TeaStoreRank {
    Dao(0, "道字号"),
    Tian(1, "天字号"),
    Di(2, "地字号");

    private final int value;
    private final String decription;


    TeaStoreRank(int value, String description) {
        this.value = value;
        this.decription = description;
    }

    public static String getString(int value) {
        return getRank(value).toString();
    }


    public static TeaStoreRank getRank(int value) {
        for (TeaStoreRank rank : TeaStoreRank.values()) {
            if (rank.value == value) {
                return rank;
            }
        }
        return null;
    }

    public String toString() {
        return decription;
    }
}
