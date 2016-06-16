package com.priavteTeaStore.zforTest.service;

import com.priavteTeaStore.domain.*;
import com.priavteTeaStore.msg.CreateTradeOrderMsg;
import com.priavteTeaStore.msg.ProductInfo;
import com.priavteTeaStore.repository.DeliveryAddressRepository;
import com.priavteTeaStore.repository.UserRepository;
import com.priavteTeaStore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
@Service
@Transactional
public class SystemTestService {

    @Autowired
    CheckCodeService checkCodeService;

    @Autowired
    RegisterService registService;

    @Autowired
    LoginService loginService;

    @Autowired
    ProductService productService;

    @Autowired
    ShopService shopService;

    @Autowired
    TradeOrderService tradeOrderService;

    @Autowired
    DeliveryAddressService deliveryAddressService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductSpecificationService productSpecificationService;

    @Autowired
    PrivateMakerService privateMakerService;

    @Autowired
    TeaStoreCardService teaStoreCardService;

    @Autowired
    ReceivePresentService receivePresentService;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;


    @Autowired
    TeaProductPrivateService teaProductPrivateService;

    Long userId;
    Long teaStoreCardProductId;
    Long deliveryAddressId;
    Long productSpecificationId;
    Long deliveryOrderId;
    Long presentOrderId;
    Long teaStoreCardId;
    Long second_userId;
    Long teaProductId;
    AccessToken accessToken;

    User getUser(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public Long regAndLogin(String phoneNum) {
        User user = registService.regist(checkCodeService.getCheckCode(phoneNum, CheckCodeType.Register),
                "md5_key", "password", phoneNum);
        userId = user.getId();
        accessToken = loginService.getLoginAccessToken(phoneNum, "md5_key");
        return userId;
    }


    public CreateTradeOrderMsg makeCreateTradeOrderMsg() {
        String app_key = "123";
        String signature = "2345";

        CreateTradeOrderMsg msg = new CreateTradeOrderMsg();
        msg.setShopId(Long.valueOf(1));
        msg.setTotalPrice(Double.valueOf(123.4));
        msg.setTotalProductCount(123);
        msg.setPayType(0);
        msg.setAddressId(Long.valueOf(1));

        msg.setAccessInfo(new AccessInfo(app_key, signature, accessToken.getAccess_token(),
                accessToken.getAccess_token_secret()));

        List<ProductInfo> productList = new ArrayList<>();
        productList.add(new ProductInfo(teaStoreCardProductId, 2));
        msg.setProductList(productList);
        System.out.println(msg.toString());
        return msg;
    }


    public void prepareProduct() {
        Long shopId = shopService.addShop("私家茶庄").getId();
        Long categoryId = productService.addCategory("da hong pao").getId();

        int output = 100;//产量
        TeaStoreCardProduct product = productService.addProduct(categoryId, shopId, new ClientTeaStoreInfo("dongfusong", 1234, 1234, output,
                new TeaStorePosition(0.0, 0.0, 0.0, 0.0, 12354.9), 1, 1));
        teaStoreCardProductId = product.getId();
    }


    public Long prepareOrder() {
        CreateTradeOrderMsg msg = makeCreateTradeOrderMsg();
        TradeOrder order = tradeOrderService.createAndSaveTradeOrder(userRepository.getOne(userId), msg);
        return order.getOrderId();
    }


    public Long getUserId() {
        return userId;
    }

    public void addAddresss() {
        DeliveryAddress deliveryAddress = deliveryAddressService.add(new DeliveryAddress("dongfusong", "13130091890",
                "dawanglu 133hao", userId));

        deliveryAddressId = deliveryAddress.getId();
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void addSpecification() {
        ProductSpecification specification = productSpecificationService.add(new ProductSpecification(122));
        productSpecificationId = specification.getId();
    }

    public Long getSpecficationId() {
        return productSpecificationId;
    }


    /**
     * 注册，登陆，添加地址，添加商品
     */
    public void preCondition() {
        String phoneNum = "13130091890";
        regAndLogin(phoneNum);
        prepareProduct();
        addAddresss();
        addSpecification();
    }

    /**
     * 购买一个茶庄卡
     */
    public void preBuyTeaStoreCard() {
        Long orderId = prepareOrder();
        tradeOrderService.confirmOrder(orderId);
        teaStoreCardId = getUser(userId).getTeaStoreCardList().get(0).getId();
    }

    /**
     * 赠送茶庄卡
     */
    public void presentTeaStoreCard() {
        PresentOrder order = teaStoreCardService.presentTeaStoreCard(teaStoreCardId, getUser(userId));
        presentOrderId = order.getId();
    }

    /**
     * 领取茶庄卡
     */
    public void receiveTeaStoreCard() {
        second_userId = regAndLogin("18911452913");
        receivePresentService.receiveBy(presentOrderId, getUser(second_userId));
    }

    /**
     * 激活茶庄卡
     *
     * @param
     */
    public void activeStoreCard() {
        User user = userRepository.findByUserId(userId);
        teaStoreCardService.activeTeaStoreCard(user, teaStoreCardId);
    }


    /**
     * 自提订货单
     *
     * @return
     */
    public DeliveryOrder privateDeliveryOrder() {
        DeliveryOrder deliveryOrder = privateMakerService.extractProductAndSelfDelivery(getDeliveryAddressId(),
                getUser(userId), teaStoreCardProductId, getSpecficationId());
        System.out.println(deliveryOrder.toString());
        deliveryOrder.send();
        deliveryOrder.recv();
        deliveryOrderId = deliveryOrder.getId();
        return deliveryOrder;
    }


    /**
     * 提取礼品单
     *
     * @return
     */
    public PresentOrder privatePresentOrder() {
        PresentOrder order = privateMakerService.extractProductAndSendPresent(getUser(userId),
                teaStoreCardId, getSpecficationId());
        presentOrderId = order.getId();
        return order;
    }

    /**
     * 其他人领取礼品
     */
    public void receivePresentByOther() {
        second_userId = regAndLogin("18911452913");
        TeaProduct teaProduct = (TeaProduct) receivePresentService.receiveBy(presentOrderId, getUser(second_userId));
        teaProductId = teaProduct.getId();
    }

    /**
     * 自己领取礼品
     */
    public void receivePresentSelf() {
        TeaProduct teaProduct = (TeaProduct) receivePresentService.receiveBy(presentOrderId, getUser(userId));
        teaProductId = teaProduct.getId();
    }

    /**
     * 提取受赠送的茶礼
     */
    public void selfDeliveryTeaPresent() {
        teaProductPrivateService.privateForSelfDelivery(teaProductId,
                deliveryAddressRepository.getOne(getDeliveryAddressId()), getUser(second_userId));
    }

    /**
     * 继续赠送收到的茶礼
     */
    public void presentTeaPresent() {
        teaProductPrivateService.privateForPresent(teaProductId, getUser(second_userId));
    }


}
