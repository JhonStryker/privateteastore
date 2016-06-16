package com.priavteTeaStore.controller;

import com.priavteTeaStore.domain.CheckCodeType;
import com.priavteTeaStore.exception.ToClientException;
import com.priavteTeaStore.msg.CheckCheckCodeMsg;
import com.priavteTeaStore.service.AccessService;
import com.priavteTeaStore.service.CheckAccessRightService;
import com.priavteTeaStore.service.CheckCodeService;
import com.priavteTeaStore.util.ERR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class CheckCodeController extends BaseRestController {

    CheckCodeService checkCodeService;

    @Autowired
    public CheckCodeController(AccessService accessService, CheckAccessRightService checkAccessRightService, CheckCodeService checkCodeService) {
        super(accessService, checkAccessRightService);
        this.checkCodeService = checkCodeService;
    }

    @RequestMapping(value = "/checkcheckCode/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    void checkCheckCode(@RequestBody @Valid CheckCheckCodeMsg msg) {
        checkMsg(msg);
        if (!checkCodeService.checkCheckCode(msg.getPhoneNum(), CheckCodeType.Register, msg.getCheckCode())) {
            throw new ToClientException(ERR.invalid_check_code);
        }
    }

    @RequestMapping(value = "/reqcheckCode/register/{phoneNum}", method = RequestMethod.GET)
    String reqCheckCode(@PathVariable String phoneNum) {
        return checkCodeService.getCheckCode(phoneNum, CheckCodeType.Register);
    }
}
