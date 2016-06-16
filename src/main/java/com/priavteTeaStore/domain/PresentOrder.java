package com.priavteTeaStore.domain;

import com.priavteTeaStore.util.TimeUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class PresentOrder {
    @Id
    @GeneratedValue
    Long id;
    Time createTime;
    Time recvTime;

    Long senderId;
    Long recvId;

    boolean isValid = true;

    //关联的是礼物的id
    Long productId;

    //两种礼物类型：茶庄卡和礼品茶
    ProductType presentType;

    public PresentOrder(Long presentId, ProductType type, Long userId) {
        this.presentType = type;
        this.productId = presentId;
        this.senderId = userId;
        this.createTime = TimeUtil.getCurrentTime();
    }

    public PresentOrder() {
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public ProductType getPresentType() {
        return presentType;
    }

    //作废订单
    public void invalid() {
        isValid = false;
    }

    public void receiveBy(Long recvId) {
        this.recvId = recvId;
        this.recvTime = TimeUtil.getCurrentTime();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof PresentOrder) && ((PresentOrder) obj).getId().equals(getId());
    }

    @Override
    public String toString() {
        return "PresentOrder{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", recvTime=" + recvTime +
                ", senderId=" + senderId +
                ", recvId=" + recvId +
                ", isValid=" + isValid +
                ", productId=" + productId +
                ", presentType=" + presentType +
                '}';
    }
}
