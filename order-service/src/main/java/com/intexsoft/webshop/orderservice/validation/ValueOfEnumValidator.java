package com.intexsoft.webshop.orderservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value);
    }
}