package com.priavteTeaStore.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Embeddable
public class TeaStoreInfo {

    @NotNull
    String name;
    @NotNull
    Integer totalAmount;//库存
    @NotNull
    Integer totalPrice;//价格
    @NotNull
    Integer output;//产量

    @Embedded
    TeaStorePosition position;//北纬


    @ManyToOne
    TeaCategory teaCategory;
    TeaStoreRank rank;//天地道
    TeaStoreStandard teaStoreStandard;//mini，标准

    public TeaStoreInfo() {
    }

    public TeaStoreInfo(String name, Integer totalAmount, Integer totalPrice, Integer output,
                        TeaStorePosition position) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.output = output;
        this.position = position;
    }

    @Override
    public TeaStoreInfo clone() throws CloneNotSupportedException {
        TeaStoreInfo info = new TeaStoreInfo(name, totalAmount, totalPrice, output, position);
        info.setRank(rank);
        info.setTeaStoreStandard(teaStoreStandard);
        info.setTeaCategory(teaCategory);
        return info;
    }

    public void setRank(TeaStoreRank rank) {
        this.rank = rank;
    }

    public void setTeaStoreStandard(TeaStoreStandard teaStoreStandard) {
        this.teaStoreStandard = teaStoreStandard;
    }

    public void setTeaCategory(TeaCategory teaCategory) {
        this.teaCategory = teaCategory;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void decrease(int amount) {
        totalAmount -= amount;
    }
}
