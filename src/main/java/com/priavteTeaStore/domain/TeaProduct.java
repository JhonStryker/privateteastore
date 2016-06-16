package com.priavteTeaStore.domain;

import com.priavteTeaStore.domain.inter.CollectionItem;
import com.priavteTeaStore.domain.inter.Present;
import com.priavteTeaStore.domain.inter.PresentOwner;

import javax.persistence.*;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class TeaProduct implements CollectionItem, Present {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    User owner;

    @OneToOne(cascade = CascadeType.ALL)
    PresentOrder presentOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    PresentOwnerRecord presentOwnerRecord;


    int amount = 0;

    public TeaProduct() {
    }

    /**
     * 通过 ProductSpecification 初始化TeaProduct
     *
     * @param specification
     */
    @Override
    public void init(ProductSpecification specification) {
        amount = specification.getExtractAmount();
    }

    @Override
    public int getAmount() {
        return amount;
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
    public void setPresentOwnerRecord(PresentOwnerRecord ownerRecord) {
        this.presentOwnerRecord = ownerRecord;
    }

    @Override
    public ProductType getPresentType() {
        return ProductType.TeaProduct;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof TeaProduct) && ((TeaProduct) (obj)).getId().equals(getId());
    }
}
