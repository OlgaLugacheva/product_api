package com.sparkequation.spring.trial.api.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FutureValidator implements ConstraintValidator<ExpirationDateConstraint, Date> {

    @Override
    public void initialize(ExpirationDateConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date == null) return true;
        else {
            LocalDate value = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            return value.isAfter(LocalDate.now().plusDays(30));
        }

    }
}