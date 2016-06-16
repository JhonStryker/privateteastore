package com.priavteTeaStore.msg;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class ProductInfo {
    Long productId;
    Integer totalCount;

    public ProductInfo() {
    }

    public ProductInfo(Long productId, int count) {
        this.productId = productId;
        this.totalCount = count;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId=" + productId +
                ", totalCount=" + totalCount +
                '}';
    }
}
