package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.DeliveryAddress;
import com.priavteTeaStore.repository.DeliveryAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
public class DeliveryAddressService {

    @Autowired
    DeliveryAddressRepository deliveryRepository;

    public DeliveryAddress add(DeliveryAddress address) {
        return deliveryRepository.save(address);
    }
}
