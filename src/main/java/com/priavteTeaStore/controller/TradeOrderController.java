package com.priavteTeaStore.controller;

import com.priavteTeaStore.msg.ClientTradeOrder;
import com.priavteTeaStore.msg.ConfirmOrderMsg;
import com.priavteTeaStore.msg.CreateTradeOrderMsg;
import com.priavteTeaStore.msg.RequestTradeOrderListMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@Controller
public class TradeOrderController extends BaseRestController {

    TradeOrderService tradeOrderService;

    @Autowired
    public TradeOrderController(AccessService accessService,
                                CheckAccessRightService checkAccessRightService, TradeOrderService tradeOrderService) {
        super(accessService, checkAccessRightService);
        this.tradeOrderService = tradeOrderService;
    }


    @RequestMapping(value = "/createTradeOrder", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createTradOrder(@RequestBody @Valid CreateTradeOrderMsg msg) {
        checkMsg(msg);
        tradeOrderService.createAndSaveTradeOrder(getUser(msg), msg);
    }

    @RequestMapping(value = "/listOrder/{orderStatus}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientTradeOrder> getTradeOrdeList(@PathVariable Integer orderStatus, @RequestBody RequestTradeOrderListMsg msg) {
        checkMsg(msg);
        return tradeOrderService.listTradeOrders(msg.getPageRequest(), getUser(msg), orderStatus);
    }

    //Todo:确认支付接口，需要修改
    @RequestMapping(value = "/tradeOrder/confirm", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void confirmOrder(@Valid @RequestBody ConfirmOrderMsg msg) {
        checkMsg(msg);
        tradeOrderService.confirmOrder(msg.getOrderId());
    }

}
