package com.priavteTeaStore.service;


import com.priavteTeaStore.domain.*;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.repository.*;
import com.priavteTeaStore.util.ERR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * Created by Thoughtworks on 16/6/9.
 */

/**
 * 私人定制服务
 */
@Service
@Transactional
public class PrivateMakerService {

    @Autowired
    TeaStoreCardRepository teaStoreCardRepository;

    @Autowired
    TeaProductRepository teaProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductSpecificationRepository productSpecificationRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    PresentOrderService presentOrderService;

    @Autowired
    DeliveryOrderService deliveryOrderService;

    /**
     * 私人定制茶礼并发送赠送订单
     * 1. 产生茶礼 2. 产生赠送订单
     * @param user
     * @param teaStoreCardId
     * @param productSpecificationId
     * @return
     */
    public PresentOrder extractProductAndSendPresent(User user, Long teaStoreCardId, Long productSpecificationId) {
        ProductSpecification productSpecification = getProductSpecification(productSpecificationId);
        TeaProduct product = addTeaProductToUser(user, teaStoreCardId, productSpecification);
        return presentOrderService.sendPresent(product, user, ProductType.TeaProduct);
    }

    /**
     * 私人定制茶礼并自提
     * 1. 产生茶礼 2. 产生自提订单
     */
    /**
     * @param deliveryAddressId
     * @param user
     * @param teaStoreCardId
     * @param productSpecificationId
     * @return
     */
    public DeliveryOrder extractProductAndSelfDelivery(Long deliveryAddressId, User user, Long teaStoreCardId,
                                                       Long productSpecificationId) {
        ProductSpecification productSpecification = getProductSpecification(productSpecificationId);
        TeaProduct teaProduct = addTeaProductToUser(user, teaStoreCardId, productSpecification);
        teaProduct.getPresentOwnerRecord().finish(PresentOwnerRecord.FinishWay.self_delivery);
        return deliveryOrderService.createAndSave(teaProduct.getId(), user.getId(),
                getDeliveryAddress(deliveryAddressId));

    }

    /**
     * 获取规格
     *
     * @param productSpecificationId
     * @return
     */
    private ProductSpecification getProductSpecification(Long productSpecificationId) {
        ProductSpecification productSpecification = productSpecificationRepository.getOne(productSpecificationId);
        if (null == productSpecification) {
            throw new ToClientException(ERR.no_such_spec, productSpecificationId.toString());
        }
        return productSpecification;
    }

    /**
     * 进行自提，并加入到用户列表
     *
     * @param user
     * @param teaStoreCardId
     * @param productSpecification
     * @return
     */
    private TeaProduct addTeaProductToUser(User user, Long teaStoreCardId, ProductSpecification productSpecification) {
        TeaProduct teaProduct = extractProduct(teaStoreCardId, productSpecification);
        user.addPresentToOwner(teaProduct);
        return teaProduct;
    }

    /**
     * 获取配送地址
     *
     * @param deliveryAddressId
     * @return
     */
    private DeliveryAddress getDeliveryAddress(Long deliveryAddressId) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.getOne(deliveryAddressId);
        if (null == deliveryAddress) {
            throw new ToClientException(ERR.no_such_address, deliveryAddressId.toString());
        }
        return deliveryAddress;
    }

    /**
     * 根据规格定制茶礼
     * @param teaStoreCardId
     * @param specification
     * @return
     */
    private TeaProduct extractProduct(Long teaStoreCardId, ProductSpecification specification) {
        TeaStoreCard card = teaStoreCardRepository.findById(teaStoreCardId);
        if (card == null) {
            throw new ToClientException(ERR.no_such_card);
        }
        TeaProduct product = (TeaProduct) (card.extract(specification));
        if (product == null) {
            throw new ToClientException(ERR.can_not_extract_product);
        }
        teaProductRepository.save(product);
        return product;
    }

}
