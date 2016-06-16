package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.DeliveryAddress;
import com.priavteTeaStore.domain.DeliveryOrder;
import com.priavteTeaStore.repository.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class DeliveryOrderService {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    /**
     * 生成提货单
     *
     * @param productId
     * @param userId
     * @param address
     * @return
     */
    public DeliveryOrder createAndSave(Long productId, Long userId, DeliveryAddress address) {
        DeliveryOrder order = new DeliveryOrder(productId, userId, address);
        return deliveryOrderRepository.save(order);
    }
}
