package com.priavteTeaStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class TeaCategory {
    @Id
    @GeneratedValue
    Long id;
    String name;//茶名称

    public TeaCategory(String name) {
        this.name = name;
    }

    public TeaCategory() {
    }

    public Long getId() {
        return id;
    }
}
