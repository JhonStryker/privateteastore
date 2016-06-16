package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.ProductSpecification;
import com.priavteTeaStore.repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class ProductSpecificationService {

    @Autowired
    ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecification add(ProductSpecification specification) {
        return productSpecificationRepository.save(specification);
    }

}
