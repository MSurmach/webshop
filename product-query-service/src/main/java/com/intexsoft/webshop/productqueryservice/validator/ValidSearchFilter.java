package com.intexsoft.webshop.productqueryservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductFilterConditionValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSearchFilter {
    String message() default "Invalid search filter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
