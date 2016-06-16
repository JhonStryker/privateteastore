package com.priavteTeaStore.domain;

import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.util.ERR;

import javax.persistence.Embeddable;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Embeddable
public class DeliveryOrderStatus {

    Status status = Status.create;

    public DeliveryOrderStatus() {
    }

    public DeliveryOrderStatus(Status status) {
        this.status = status;
    }

    public void convertTo(Status nextStatus) {
        if (1 == nextStatus.getValue() - this.status.getValue()) {
            this.status = nextStatus;
        } else {
            throw new ToClientException(ERR.delievery_order_status_err);
        }
    }

    boolean isFinished() {
        return status == Status.finish;
    }

    enum Status {
        create(0),
        send(1),
        finish(2);
        int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
