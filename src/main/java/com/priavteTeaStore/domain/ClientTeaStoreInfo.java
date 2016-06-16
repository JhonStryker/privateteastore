package com.priavteTeaStore.domain;

import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.util.ERR;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class ClientTeaStoreInfo {

    String name;//茶庄商品名称
    Integer totalAmount;//库存

    Integer totalPrice;//价格
    Integer output;//产量

    TeaStorePosition position;//东经

    Integer teaStoreStandard;//mini，标准
    Integer rank;//天地道

    public ClientTeaStoreInfo() {
    }

    public ClientTeaStoreInfo(String name, Integer totalAmount, Integer totalPrice,
                              Integer output, TeaStorePosition position, Integer teaStoreStandard, Integer rank) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.output = output;
        this.position = position;
        this.teaStoreStandard = teaStoreStandard;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getTeaStoreStandard() {
        return teaStoreStandard;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getOutput() {
        return output;
    }


    /**
     * 将对外接口转换成对内结构
     *
     * @param category
     * @return
     */
    public TeaStoreInfo toInnerTeaStoreInfo(TeaCategory category) {
        TeaStoreInfo info = new TeaStoreInfo(name, totalAmount, totalPrice, output, position);
        TeaStoreRank r = TeaStoreRank.getRank(rank);
        if (null == r) {
            throw new ToClientException(ERR.no_such_rank, rank.toString());
        }
        info.setRank(r);
        TeaStoreStandard s = TeaStoreStandard.getStandard(teaStoreStandard);
        if (null == s) {
            throw new ToClientException(ERR.no_such_standard, teaStoreStandard.toString());
        }
        info.setTeaStoreStandard(s);
        info.setTeaCategory(category);
        return info;
    }
}

