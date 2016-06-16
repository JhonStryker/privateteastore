package com.priavteTeaStore.common.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AccessValidator.class) // 具体的实现
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface AccessValid {
	String message() default "access denied!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
