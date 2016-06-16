package com.priavteTeaStore.domain.inter;

import com.priavteTeaStore.domain.PresentOwnerRecord;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public interface PresentOwner {

    Long getId();

    default void removeFromPreUser(Present present) {
        PresentOwner preOwner = present.getOwner();
        if (preOwner != null) {
            preOwner.removeFromPrivateGoodList(present);
        }
    }

    /**
     * 从之前的owner中剔除，加入到当前的owner中，
     *
     * @param present
     */
    default void addPresentToOwner(Present present) {
        removeFromPreUser(present);
        addToPrivateGoodList(present);
        present.setOwner(this);
        updateOwnerRecord(present);
    }


    /**
     * 更新礼物所属记录
     *
     * @param present
     */
    default void updateOwnerRecord(Present present) {
        PresentOwnerRecord preRecord = present.getPresentOwnerRecord();
        if (preRecord != null) {
            preRecord.finish(PresentOwnerRecord.FinishWay.received_by);
        }
        PresentOwnerRecord record = new PresentOwnerRecord(present.getId(), present.getPresentType(), getId());
        present.setPresentOwnerRecord(record);
    }

    void removeFromPrivateGoodList(Present present);

    void addToPrivateGoodList(Present present);
}
