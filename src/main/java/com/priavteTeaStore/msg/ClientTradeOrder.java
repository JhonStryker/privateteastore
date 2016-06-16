package com.priavteTeaStore.msg;

import com.priavteTeaStore.domain.TradeOrder;

/**
 * Created by Thoughtworks on 16/6/14.
 */
public class ClientTradeOrder {

    public ClientTradeOrder() {
    }

    public ClientTradeOrder(TradeOrder order) {
    }

    public static ClientTradeOrder convert(TradeOrder order) {
        return new ClientTradeOrder(order);
    }
}
