package com.priavteTeaStore.service;

import com.priavteTeaStore.domain.ClientTeaStoreInfo;
import com.priavteTeaStore.domain.Shop;
import com.priavteTeaStore.domain.TeaCategory;
import com.priavteTeaStore.domain.TeaStoreCardProduct;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.msg.ClientTeaStore;
import com.priavteTeaStore.repository.ShopRepository;
import com.priavteTeaStore.repository.TeaCategoryRepository;
import com.priavteTeaStore.repository.TeaStoreCardProductRepository;
import com.priavteTeaStore.util.ERR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class ProductService {

    private final static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    TeaCategoryRepository teaCategoryRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    TeaStoreCardProductRepository teaStoreCardProductRepository;

    public TeaCategory addCategory(String categoryName) {
        TeaCategory category = teaCategoryRepository.findByName(categoryName);
        if (category != null) {
            throw new ToClientException(ERR.already_add_teaProduct, categoryName);
        }
        return teaCategoryRepository.save(new TeaCategory(categoryName));
    }

    public TeaStoreCardProduct addProduct(Long categoryId, Long shopId, ClientTeaStoreInfo clientTeaStoreInfo) {
        TeaCategory category = teaCategoryRepository.findById(categoryId);
        Shop shop = shopRepository.findById(shopId);
        if (shop == null || category == null) {
            throw new ToClientException(ERR.can_not_find_category_or_shop, categoryId + " and " + shopId);
        }
        TeaStoreCardProduct product = new TeaStoreCardProduct(clientTeaStoreInfo.toInnerTeaStoreInfo(category), shop);
        return teaStoreCardProductRepository.save(product);
    }

    public Page<ClientTeaStore> listProduct(Long categoryId, PageRequest pageRequest) {
        Page<TeaStoreCardProduct> pages;
        final Long nullCategory = Long.valueOf(0);
        if (categoryId.equals(nullCategory)) {
            pages = teaStoreCardProductRepository.findAll(pageRequest);
        } else {
            TeaCategory category = teaCategoryRepository.findById(categoryId);
            pages = teaStoreCardProductRepository.findByTeaStoreTeaCategory(category, pageRequest);
        }
        return pages.map(ClientTeaStore::convert);
    }
}
