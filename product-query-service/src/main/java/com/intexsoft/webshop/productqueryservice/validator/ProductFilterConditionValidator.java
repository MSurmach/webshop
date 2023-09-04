package com.intexsoft.webshop.productqueryservice.validator;

import com.intexsoft.webshop.productqueryservice.dto.FilterConditionDto;
import com.intexsoft.webshop.productqueryservice.model.Product;
import com.intexsoft.webshop.productqueryservice.model.ShopProductLink;
import com.intexsoft.webshop.productqueryservice.service.FilterOperation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.intexsoft.webshop.productqueryservice.service.FilterOperation.*;

public class ProductFilterConditionValidator implements
        ConstraintValidator<ValidSearchFilter, List<FilterConditionDto>> {
    private static final Map<String, Set<FilterOperation>> FIELD_NAME_AVAILABLE_OPERATION_MAP
            = Map.of(
            ShopProductLink.Fields.shopName, Set.of(EQUAL, NOT_EQUAL),
            Product.Fields.productName, Set.of(EQUAL, NOT_EQUAL),
            ShopProductLink.Fields.price, Set.of(EQUAL, NOT_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL_TO, LESS_THAN, LESSTHAN_OR_EQUAL_TO)
    );

    @Override
    public void initialize(ValidSearchFilter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<FilterConditionDto> filterConditionDtos, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        for (FilterConditionDto filterConditionDto : filterConditionDtos) {
            String fieldName = filterConditionDto.getFieldName();
            Set<FilterOperation> availableFilterOperations = FIELD_NAME_AVAILABLE_OPERATION_MAP.get(fieldName);
            if (Objects.isNull(availableFilterOperations)) {
                customMessageForValidation(context, String.format("fieldName = '%s' is not valid", fieldName));
                return false;
            }
            FilterOperation givenFilterOperation = filterConditionDto.getFilterOperation();
            if (!availableFilterOperations.contains(givenFilterOperation)) {
                customMessageForValidation(context,
                        String.format("operation = '%s' can't be applied to fieldName = '%s'. Available operations are %s.",
                                givenFilterOperation.toString(), fieldName, availableFilterOperations));
                return false;
            }
        }
        return true;
    }

    private void customMessageForValidation(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
