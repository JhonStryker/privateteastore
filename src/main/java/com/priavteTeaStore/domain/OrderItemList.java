package com.priavteTeaStore.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Embeddable
public class OrderItemList {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tradeOrder")
    List<OrderItem> orderItemList = new ArrayList<>();

    public List<TeaStoreCardProduct> getProductList() {
        List<TeaStoreCardProduct> list = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            list.add(orderItem.getProduct());
        }
        return list;
    }

    public void addOrderItem(TradeOrder order, OrderItem item) {
        orderItemList.add(item);
        item.setTradeOrder(order);
    }
}
