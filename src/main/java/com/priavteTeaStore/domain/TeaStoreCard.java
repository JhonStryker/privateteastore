package com.priavteTeaStore.domain;

import com.priavteTeaStore.domain.inter.Collection;
import com.priavteTeaStore.domain.inter.CollectionItem;
import com.priavteTeaStore.domain.inter.Present;
import com.priavteTeaStore.domain.inter.PresentOwner;

import javax.persistence.*;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class TeaStoreCard implements Present, Collection {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne
    PresentOrder presentOrder;

    @ManyToOne
    User owner;

    @Embedded
    TeaStoreInfo teaStore;

    Long teaStoreProductId;

    @ManyToOne(cascade = CascadeType.ALL)
    PresentOwnerRecord presentOwnerRecord;

    @Embedded
    TeaStoreCardStatus status = new TeaStoreCardStatus();

    public TeaStoreCard() {

    }

    public TeaStoreCard(TeaStoreCardProduct product) {
        this.teaStoreProductId = product.getId();
        try {
            this.teaStore = product.getTeaStore().clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getTotalAmount() {
        return teaStore.getTotalAmount();
    }

    @Override
    public void decrease(int amount) {
        teaStore.decrease(amount);
    }

    @Override
    public CollectionItem createItem() {
        return new TeaProduct();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public PresentOrder getPresentOrder() {
        return presentOrder;
    }

    @Override
    public void setPresentOrder(PresentOrder order) {
        this.presentOrder = order;
    }


    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(PresentOwner owner) {
        this.owner = (User) owner;
    }

    @Override
    public PresentOwnerRecord getPresentOwnerRecord() {
        return presentOwnerRecord;
    }

    @Override
    public void setPresentOwnerRecord(PresentOwnerRecord record) {
        this.presentOwnerRecord = record;
    }

    @Override
    public ProductType getPresentType() {
        return ProductType.TeaStoreCard;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof TeaStoreCard) && ((TeaStoreCard) (obj)).getId().equals(getId());
    }

    public void active() {
        status.active();
        presentOwnerRecord.finish(PresentOwnerRecord.FinishWay.active);
    }

    public boolean isActiveTeaStore() {
        return status.isActiveTeaStore();
    }
}
