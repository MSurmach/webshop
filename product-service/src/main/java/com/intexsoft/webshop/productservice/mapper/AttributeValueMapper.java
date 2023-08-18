package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueDto;
import com.intexsoft.webshop.productservice.model.AttributeValue;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AttributeValueMapper {
    List<AttributeValueDto> toAttributeDtoList(Set<AttributeValue> attributeValues);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "attributeId", source = "attributeValue.attribute.id")
    AttributeValueDto attributeValueToAttributeValueDto(AttributeValue attributeValue);
}