package com.priavteTeaStore.domain;

import javax.persistence.*;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class TeaStoreCardProduct {
    @Id
    @GeneratedValue
    Long id;

    @Embedded
    TeaStoreInfo teaStore;

    @ManyToOne
    Shop shop;

    public TeaStoreCardProduct() {
    }

    public TeaStoreCardProduct(TeaStoreInfo teaStore, Shop shop) {
        this.teaStore = teaStore;
        this.shop = shop;
    }

    public TeaStoreInfo getTeaStore() {
        return teaStore;
    }

    //    public int getTotalAmount() {
//        return teaStore.getTotalAmount();
//    }
//
//    public void decreaseAmount(int amount) {
//        teaStore.decrease(amount);
//    }

    public Long getId() {
        return id;
    }
}
