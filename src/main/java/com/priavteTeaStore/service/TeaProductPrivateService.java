package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.*;
import com.priavteTeaStore.repository.TeaProductRepository;
import com.priavteTeaStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class TeaProductPrivateService {
    @Autowired
    TeaProductRepository teaProductRepository;

    @Autowired
    PresentOrderService presentOrderService;

    @Autowired
    DeliveryOrderService deliveryOrderService;

    @Autowired
    UserRepository userRepository;

    public void privateForPresent(Long teaProductId, User user) {
        TeaProduct product = teaProductRepository.findById(teaProductId);
        presentOrderService.sendPresent(product, user, ProductType.TeaProduct);
    }

    public void privateForSelfDelivery(Long teaProductId, DeliveryAddress address, User user) {
        TeaProduct product = teaProductRepository.findById(teaProductId);
        deliveryOrderService.createAndSave(product.getId(), user.getId(), address);
        product.getPresentOwnerRecord().finish(PresentOwnerRecord.FinishWay.self_delivery);
    }
}
