package com.priavteTeaStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class ProductSpecification {
    @Id
    @GeneratedValue
    Long id;
    private int extractAmount;

    public ProductSpecification(int extractAmount) {
        this.extractAmount = extractAmount;
    }

    public ProductSpecification() {
    }

    public Long getId() {
        return id;
    }

    public int getExtractAmount() {
        return extractAmount;
    }
}
