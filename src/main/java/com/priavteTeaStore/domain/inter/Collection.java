package com.priavteTeaStore.domain.inter;

import com.priavteTeaStore.domain.ProductSpecification;

/**
 * Created by Thoughtworks on 16/6/9.
 */

public interface Collection {
    int getTotalAmount();

    void decrease(int amount);

    default boolean canExtract(ProductSpecification spec) {
        return getTotalAmount() > spec.getExtractAmount();
    }

    default CollectionItem extract(ProductSpecification spec) {
        if (canExtract(spec)) {
            decrease(spec.getExtractAmount());
            CollectionItem item = createItem();
            item.init(spec);
            return item;
        }
        return null;
    }

    CollectionItem createItem();
}