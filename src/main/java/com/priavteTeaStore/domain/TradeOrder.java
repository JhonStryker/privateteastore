package com.priavteTeaStore.domain;

import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.util.ERR;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class TradeOrder {

    @Id
    @GeneratedValue
    Long orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    PayType payType;

    Long addressId;

    Double totalPrice;
    OrderStatus status = OrderStatus.create;

    @Embedded
    OrderItemList orderItemList = new OrderItemList();

    public TradeOrder() {
    }

    public TradeOrder(User user, PayType payType, Double totalPrice, Long addressId) {
        this.user = user;
        this.payType = payType;
        this.totalPrice = totalPrice;
        this.addressId = addressId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    /**
     * 1. 修改订单状态 2.把订单中的商品加入购买人list中
     */
    public void finish() {
        changeStatus();
    }

    /**
     * 修改订单状态
     */
    private void changeStatus() {
        if (this.status == OrderStatus.paid_and_finish) {
            throw new ToClientException(ERR.has_already_paid);
        }
        this.status = OrderStatus.paid_and_finish;
    }

    /**
     * 把私家茶庄加入购买人的私人物品中
     */
    public List<TeaStoreCardProduct> getTeaStoreCardProductList() {
        return orderItemList.getProductList();
    }

    /**
     * 添加订单子项目
     *
     * @param orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItemList.addOrderItem(this, orderItem);
    }

    public enum OrderStatus {
        create,
        paid_and_finish,
        invalid_status
    }

}
