package com.priavteTeaStore.domain;

import com.priavteTeaStore.util.TimeUtil;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class DeliveryOrder {
    @Id
    @GeneratedValue
    Long id;

    Long productId;
    Time createTime;
    Time finishTime;
    Long ownerId;
    @OneToOne
    DeliveryAddress address;
    @Embedded
    DeliveryOrderStatus status = new DeliveryOrderStatus();

    public DeliveryOrder() {
    }

    public DeliveryOrder(Long productId, Long userId, DeliveryAddress address) {
        this.productId = productId;
        this.ownerId = userId;
        this.address = address;
        this.createTime = TimeUtil.getCurrentTime();
    }

    public Long getProductId() {
        return productId;
    }

    public void send() {
        status.convertTo(DeliveryOrderStatus.Status.send);
    }

    public void recv() {
        status.convertTo(DeliveryOrderStatus.Status.finish);
        this.finishTime = TimeUtil.getCurrentTime();
    }

    public boolean isFinished() {
        return status.isFinished();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "id=" + id +
                ", productId=" + productId +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", ownerId=" + ownerId +
                ", address=" + address +
                ", status=" + status +
                '}';
    }
}
