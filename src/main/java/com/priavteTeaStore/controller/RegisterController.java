package com.priavteTeaStore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.priavteTeaStore.msg.RegisterMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.service.RegisterService;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class RegisterController extends BaseRestController {

    RegisterService registerService;

    @Autowired
    public RegisterController(AccessService accessService,
                              CheckAccessRightService checkAccessRightService, RegisterService registerService) {
        super(accessService, checkAccessRightService);
        this.registerService = registerService;
    }

    @RequestMapping("/regist")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody @Valid RegisterMsg registerMsg) {
        checkMsg(registerMsg);
        registerService.regist(registerMsg.getCheck_code(), registerMsg.getMd5_key(),
                registerMsg.getPassword(), registerMsg.getPhone_num());
    }

}

