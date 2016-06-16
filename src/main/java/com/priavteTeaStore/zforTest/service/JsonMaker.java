package com.priavteTeaStore.zforTest.service;

import com.priavteTeaStore.domain.AccessInfo;
import com.priavteTeaStore.domain.ClientTeaStoreInfo;
import com.priavteTeaStore.msg.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 16/6/9.
 */
public class JsonMaker {

    @Test
    public void makeCreateTradeOrderMsg() {
        CreateTradeOrderMsg msg = new CreateTradeOrderMsg();
        msg.setShopId(Long.valueOf(1));
        msg.setTotalPrice(Double.valueOf(123.4));
        msg.setTotalProductCount(123);
        msg.setPayType(0);

        msg.setAccessInfo(new AccessInfo("123", "dfadsfadf", "d8c2a01dae5b4b74840da63b8c2eae74", "13130091890"));

        List<ProductInfo> productList = new ArrayList<>();
        productList.add(new ProductInfo(Long.valueOf(1), 2));
        msg.setProductList(productList);

        System.out.println(JsonConverter.convertToJson(msg));
    }


    @Test
    public void makeRequestTradeOrderListMsg() {
        RequestTradeOrderListMsg msg = new RequestTradeOrderListMsg(2, 3);
        msg.setAccessInfo(new AccessInfo("123", "dfadsfadf", "d8c2a01dae5b4b74840da63b8c2eae74", "13130091890"));
        System.out.println(JsonConverter.convertToJson(msg));
    }

    @Test
    public void makeCheckCheckCodeMsg() {
        CheckCheckCodeMsg msg = new CheckCheckCodeMsg("13130091890", "735615");
        System.out.println(JsonConverter.convertToJson(msg));
    }

    @Test
    public void makeRegistMsg() {
        RegisterMsg msg = new RegisterMsg("12345", "123456", "111111", "13130091890");
        System.out.println(JsonConverter.convertToJson(msg));
    }

    @Test
    public void makeAddProductMsg() {
        AddProductMsg msg = new AddProductMsg(Long.valueOf(1), Long.valueOf(1),
                new ClientTeaStoreInfo());
        System.out.println(JsonConverter.convertToJson(msg));
    }

}
