package com.intexsoft.webshop.productqueryservice.service;

import com.intexsoft.webshop.productqueryservice.dto.FilterConditionDto;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class ProductCriteriaBuilder {
    private static final Map<FilterOperation, Function<FilterConditionDto, Criteria>> FILTER_CRITERIA = new HashMap<>();

    static {
        FILTER_CRITERIA.put(FilterOperation.EQUAL,
                condition -> buildWhereCriteria(condition.getFieldName()).is(condition.getFieldValue()));
        FILTER_CRITERIA.put(FilterOperation.NOT_EQUAL,
                condition -> buildWhereCriteria(condition.getFieldName()).ne(condition.getFieldValue()));
        FILTER_CRITERIA.put(FilterOperation.GREATER_THAN,
                condition -> buildWhereCriteria(condition.getFieldName()).gt(condition.getFieldValue()));
        FILTER_CRITERIA.put(FilterOperation.GREATER_THAN_OR_EQUAL_TO,
                condition -> buildWhereCriteria(condition.getFieldName()).gte(condition.getFieldValue()));
        FILTER_CRITERIA.put(FilterOperation.LESS_THAN,
                condition -> buildWhereCriteria(condition.getFieldName()).lt(condition.getFieldValue()));
        FILTER_CRITERIA.put(FilterOperation.LESSTHAN_OR_EQUAL_TO,
                condition -> buildWhereCriteria(condition.getFieldName()).lte(condition.getFieldValue()));
    }

    private static final Map<String, String> FIELD_NAME_DOCUMENT_PATH_PREFIX = Map.of(
            "productName", "",
            "shopName", "productShopLink.",
            "price", "productShopLink."
    );

    public Criteria buildCriteria(List<FilterConditionDto> filterConditionDtos) {
        Criteria criteria = new Criteria();
        if (filterConditionDtos.isEmpty()) {
            return criteria;
        }
        List<Criteria> criteriasBasedOnConditions = filterConditionDtos.stream().map(this::buildFilterConditionCriteria).toList();
        return new Criteria().andOperator(criteriasBasedOnConditions);
    }

    private Criteria buildFilterConditionCriteria(FilterConditionDto condition) {
        return FILTER_CRITERIA.get(condition.getFilterOperation()).apply(condition);
    }

    private static Criteria buildWhereCriteria(String fieldName) {
        String documentPathPrefix = FIELD_NAME_DOCUMENT_PATH_PREFIX.get(fieldName);
        return Criteria.where(documentPathPrefix + fieldName);
    }
}