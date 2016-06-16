package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.ProductType;
import com.priavteTeaStore.domain.inter.Present;
import com.priavteTeaStore.repository.TeaProductRepository;
import com.priavteTeaStore.repository.TeaStoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
public class PresentManger {
    @Autowired
    TeaStoreCardRepository teaStoreCardRepository;

    @Autowired
    TeaProductRepository teaProductRepository;

    //持有PresentId的对象也要持有presentType
    public Present getPresent(ProductType type, Long id) {
        if (type == ProductType.TeaStoreCard) {
            return teaStoreCardRepository.findById(id);
        }
        return teaProductRepository.findById(id);
    }
}
