package com.priavteTeaStore.controller;

import com.priavteTeaStore.domain.User;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.msg.base.ClientRequestMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.util.ERR;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class BaseRestController {

    AccessService accessService;
    CheckAccessRightService checkAccessRightService;

    public BaseRestController(AccessService accessService, CheckAccessRightService checkAccessRightService) {
        this.accessService = accessService;
        this.checkAccessRightService = checkAccessRightService;
    }

    public BaseRestController() {

    }

    /**
     * 通过消息中的字段获取User
     *
     * @param msg
     * @return
     */
    public User getUser(ClientRequestMsg msg) {
        if (null == msg.getAccessInfo()) {
            return null;
        }
        User user = accessService.getUser(msg.getAccessInfo());
        if (user == null) {
            throw new ToClientException(ERR.no_such_user);
        }
        return user;
    }

    public void checkMsg(ClientRequestMsg msg) {
        msg.checkMsg();
        //Todo:正式环境需要打开注释
//        if (!checkAccessRightService.check(msg.getAccessInfo(), msg.getCheckType())){
//            throw new ToClientException(ERR.has_no_right);
//        }
    }
}
