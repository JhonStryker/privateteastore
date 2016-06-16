package com.priavteTeaStore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Entity
public class DeliveryAddress {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String phoneNum;
    String addresssInfo;
    Long userId;

    public DeliveryAddress(String name, String phoneNum, String addresssInfo, Long userId) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.addresssInfo = addresssInfo;
        this.userId = userId;
    }

    public DeliveryAddress() {
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddresssInfo() {
        return addresssInfo;
    }

    public Long getId() {
        return id;
    }
}

