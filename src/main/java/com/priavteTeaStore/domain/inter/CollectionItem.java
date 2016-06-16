package com.priavteTeaStore.domain.inter;

import com.priavteTeaStore.domain.ProductSpecification;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public interface CollectionItem {
    void init(ProductSpecification specification);
    int getAmount();
}
