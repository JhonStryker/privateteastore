package com.priavteTeaStore.controller;

import com.priavteTeaStore.domain.DeliveryAddress;
import com.priavteTeaStore.msg.DeliveryAddressMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class DeliveryAddressController extends BaseRestController {

    DeliveryAddressService deliveryAddressService;

    @Autowired
    public DeliveryAddressController(AccessService accessService,
                                     CheckAccessRightService checkAccessRightService, DeliveryAddressService deliveryAddressService) {
        super(accessService, checkAccessRightService);
        this.deliveryAddressService = deliveryAddressService;
    }

    @RequestMapping("/deliveryAddress")
    public void add(@RequestBody @Valid DeliveryAddressMsg msg) {
        deliveryAddressService.add(new DeliveryAddress(msg.getName(), msg.getPhoneNum(), msg.getAddressInfo(), msg.getUserId()));
    }
}
