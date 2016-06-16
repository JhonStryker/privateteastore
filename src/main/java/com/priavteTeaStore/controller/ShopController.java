package com.priavteTeaStore.controller;

import com.priavteTeaStore.msg.AddShopMsg;
import com.priavteTeaStore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class ShopController extends BaseRestController {
    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addShop(@RequestBody @Valid AddShopMsg msg) {
        shopService.addShop(msg.getShopName());
    }
}
