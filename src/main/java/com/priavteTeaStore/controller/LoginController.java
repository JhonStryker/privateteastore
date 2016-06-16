package com.priavteTeaStore.controller;

import com.priavteTeaStore.domain.AccessToken;
import com.priavteTeaStore.msg.LoginMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class LoginController extends BaseRestController {

    LoginService loginService;

    @Autowired
    public LoginController(AccessService accessService, CheckAccessRightService checkAccessRightService) {
        super(accessService, checkAccessRightService);
    }

    @RequestMapping("/login")
    public AccessToken login(@Valid @RequestBody LoginMsg msg) {
        checkMsg(msg);
        return loginService.getLoginAccessToken(msg.getUserName(), msg.getApp_key());
    }
}
