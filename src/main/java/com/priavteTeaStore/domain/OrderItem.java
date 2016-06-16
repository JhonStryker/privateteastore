package com.priavteTeaStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    TradeOrder tradeOrder;

    @ManyToOne
    TeaStoreCardProduct product;

    Integer totalCount = 0;

    public OrderItem(TeaStoreCardProduct card, Integer totalCount) {
        this.product = card;
        this.totalCount = totalCount;

    }

    public OrderItem() {
    }

    public TeaStoreCardProduct getProduct() {
        return product;
    }

    public void setTradeOrder(TradeOrder tradeOrder) {
        this.tradeOrder = tradeOrder;
    }
}
