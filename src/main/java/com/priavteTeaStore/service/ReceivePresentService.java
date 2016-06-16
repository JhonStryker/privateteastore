package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.PresentOrder;
import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.domain.inter.Present;
import com.priavteTeaStore.repository.PresentOrderRepository;
import com.priavteTeaStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */

/**
 * 接受礼物服务
 */
@Service
@Transactional
public class ReceivePresentService {
    @Autowired
    PresentOrderRepository presentOrderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PresentOrderService presentOrderService;

    public Present receiveBy(Long orderId, User user) {
        PresentOrder order = presentOrderRepository.findById(orderId);
        return presentOrderService.receiveBy(order, user);
    }
}
