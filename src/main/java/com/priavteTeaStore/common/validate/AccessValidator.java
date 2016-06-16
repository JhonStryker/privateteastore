package com.priavteTeaStore.common.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.priavteTeaStore.msg.base.ClientRequestMsg;
import com.priavteTeaStore.service.CheckAccessRightService;

/**
 * 权限校验validator
 * @author zhufeng
 *
 */
@Component
public class AccessValidator implements ConstraintValidator<AccessValid, ClientRequestMsg> {
	
	@Autowired
	CheckAccessRightService checkAccessRightService;
	
	@Override
	public void initialize(AccessValid constraintAnnotation) {
	}

	@Override
	public boolean isValid(ClientRequestMsg msg, ConstraintValidatorContext context) {
		msg.checkMsg();
		return checkAccessRightService.check(msg.getAccessInfo(), msg.getCheckType());
	}

}
