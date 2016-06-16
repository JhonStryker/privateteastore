package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.Shop;
import com.priavteTeaStore.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    public Shop addShop(String name) {
        return shopRepository.save(new Shop(name));
    }
}
