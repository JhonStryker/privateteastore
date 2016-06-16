package com.priavteTeaStore.controller;

import com.priavteTeaStore.msg.ActiveStoreCardMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.service.TeaStoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class TeaStoreCardController extends BaseRestController {

    TeaStoreCardService teaStoreCardService;

    @Autowired
    public TeaStoreCardController(AccessService accessService, CheckAccessRightService checkAccessRightService,
                                  TeaStoreCardService teaStoreCardService) {
        super(accessService, checkAccessRightService);
        this.teaStoreCardService = teaStoreCardService;
    }

    @RequestMapping("/activeCard")
    @ResponseStatus(HttpStatus.OK)
    public void activeCard(@RequestBody @Valid ActiveStoreCardMsg msg) {
        checkMsg(msg);
        teaStoreCardService.activeTeaStoreCard(getUser(msg), msg.getCardId());
    }
}