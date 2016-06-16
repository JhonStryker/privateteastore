package com.priavteTeaStore.msg;

import com.priavteTeaStore.msg.base.ClientRequestMsg;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class CreateTradeOrderMsg extends ClientRequestMsg {
    @NotNull
    Long shopId;

    @NotNull
    List<ProductInfo> productList;

    @NotNull
    Double totalPrice;

    @NotNull
    Integer totalProductCount;

    @NotNull
    Long addressId;

    @NotNull
    Integer payType;


    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(Integer totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    @Override
    public CheckType getCheckType() {
        return CheckType.check_right_for_user_info;
    }

    protected boolean checkMsgContent() {
        if (productList.size() != totalProductCount || totalPrice == 0) {
            concreteErrorInfo = "数量错误或者总钱数为0";//可以增加详细信息
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreateTradeOrderMsg{" +
                "shopId=" + shopId +
                ", productList=" + productList +
                ", totalPrice=" + totalPrice +
                ", totalProductCount=" + totalProductCount +
                '}';
    }
}
