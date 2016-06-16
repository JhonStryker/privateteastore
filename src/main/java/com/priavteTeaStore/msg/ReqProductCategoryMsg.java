package com.priavteTeaStore.msg;

import javax.validation.constraints.NotNull;

/**
 * Created by Thoughtworks on 16/6/9.
 */

/**
 * 后续会有字段添加所以提取成消息
 */
public class ReqProductCategoryMsg {
    @NotNull
    String categoryName;

    public ReqProductCategoryMsg() {
    }

    public ReqProductCategoryMsg(String productName) {
        this.categoryName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
