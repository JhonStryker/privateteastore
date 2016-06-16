package com.priavteTeaStore.zforTest;

import com.priavteTeaStore.DemoForTeaStoreApplication;
import com.priavteTeaStore.zforTest.service.SystemTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoForTeaStoreApplication.class)
@WebAppConfiguration
public class Test_ExecuteByStep {

    @Autowired
    SystemTestService systemTestService;

    /**
     * 注册，登陆，添加地址，添加商品
     */
    @Test
    public void prepareProduct() {
        systemTestService.preCondition();
    }

    /**
     * 购买一个茶庄卡
     */
    @Test
    public void buyTeaStoreCard() {
        prepareProduct();
        systemTestService.preBuyTeaStoreCard();
    }


    /**
     * 赠送茶庄卡
     */
    @Test
    public void presentTeaStoreCard() {
        buyTeaStoreCard();
        systemTestService.presentTeaStoreCard();
    }


    /**
     * 领取茶庄卡
     */
    @Test
    public void receiveTeaStoreCardByOther() {
        presentTeaStoreCard();
        systemTestService.receiveTeaStoreCard();
    }

    /**
     * 激活茶庄卡
     *
     * @param
     */
    @Test
    public void activeTeaStoreCard() {
        buyTeaStoreCard();
        systemTestService.activeStoreCard();
    }


    /**
     * 自提订货单
     *
     * @return
     */
    @Test
    public void deliveryTeaProduct() {
        activeTeaStoreCard();
        systemTestService.privateDeliveryOrder();
    }

    /**
     * 提取礼品单
     *
     * @return
     */
    @Test
    public void presentTeaProduct() {
        activeTeaStoreCard();
        systemTestService.privatePresentOrder();
    }


    /**
     * 其他人领取礼品
     */
    @Test
    public void receivePresentByOther() {
        activeTeaStoreCard();
        systemTestService.privatePresentOrder();
        systemTestService.receivePresentByOther();
    }


    /**
     * 提取受赠送的茶礼
     */
    @Test
    public void selfDeliveryTeaPresent() {
        receivePresentByOther();
        systemTestService.selfDeliveryTeaPresent();
    }


    /**
     * 继续赠送收到的茶礼
     */
    @Test
    public void presentTeaPresent() {
        activeTeaStoreCard();
        systemTestService.privatePresentOrder();
        systemTestService.receivePresentByOther();
        systemTestService.presentTeaPresent();
    }


    /**
     * 自己领取礼品
     */
    @Test
    public void receivePresentBySelf() {
        activeTeaStoreCard();
        systemTestService.privatePresentOrder();
        systemTestService.receivePresentSelf();
    }
}
