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
public class PresentOwnerRecord {
    @Id
    @GeneratedValue
    Long id;
    Long ownerId;
    Long presentId;
    ProductType productType;
    Time startTime;
    Time endTime;

    FinishWay finishWay;

    public PresentOwnerRecord() {
    }

    public PresentOwnerRecord(Long presentId, ProductType type, Long ownerId) {
        this.presentId = presentId;
        this.ownerId = ownerId;
        this.productType = type;
        this.startTime = TimeUtil.getCurrentTime();
    }

    public void finish(FinishWay way) {
        finishWay = way;
        endTime = TimeUtil.getCurrentTime();
    }

    public enum FinishWay {
        received_by,
        active,
        self_delivery
    }
}
