package com.priavteTeaStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class Shop {
    @Id
    @GeneratedValue
    Long id;
    String name;

    @OneToMany(mappedBy = "shop")
    List<TeaStoreCardProduct> productList;

    public Shop(String name) {
        this.name = name;
    }

    public Shop() {
    }

    public void addProduct(TeaStoreCardProduct product) {
        productList.add(product);
    }

    public Long getId() {
        return id;
    }
}
