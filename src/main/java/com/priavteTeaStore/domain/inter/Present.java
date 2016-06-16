package com.priavteTeaStore.domain.inter;

import com.priavteTeaStore.domain.PresentOrder;
import com.priavteTeaStore.domain.PresentOwnerRecord;
import com.priavteTeaStore.domain.ProductType;
import com.priavteTeaStore.util.OperationResult;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public interface Present {
    /**
     * 对外接口：发送礼物
     *
     * @param order
     */
    default void send(PresentOrder order) {
        PresentOrder preOrder = getPresentOrder();
        if (preOrder != null) {
            preOrder.invalid();
        }
        setPresentOrder(order);
    }

    /**
     * 对外接口：接受礼物
     *
     * @param owner
     * @param order
     * @return
     */
    default OperationResult receiveBy(PresentOwner owner, PresentOrder order) {
        //如果不是当前所在的订单
        if (!isMatchPresentOrder(order)) {
            return OperationResult.failure;//考虑如何把这个信息传递出去
        }
        owner.addPresentToOwner(this);
        order.receiveBy(owner.getId());
        return OperationResult.success;
    }


    default boolean isMatchPresentOrder(PresentOrder order) {
        return getPresentOrder().equals(order);
    }

    //下面的函数需要子类持有数据，实现get，set函数
    Long getId();

    PresentOrder getPresentOrder();

    void setPresentOrder(PresentOrder order);

    PresentOwner getOwner();

    void setOwner(PresentOwner owner);

    PresentOwnerRecord getPresentOwnerRecord();//子类的PresentOwnerRecord应该是级联保存的

    void setPresentOwnerRecord(PresentOwnerRecord ownerRecord);

    ProductType getPresentType();
}