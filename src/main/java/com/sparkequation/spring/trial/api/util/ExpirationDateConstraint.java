package com.sparkequation.spring.trial.api.util;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FutureValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpirationDateConstraint {

    String message() default "Invalid expiration date: it must expire not less than 30 days since now";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}