package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeValueDto;
import com.intexsoft.webshop.productservice.dto.product.ProductAttributeValueCreateDto;
import com.intexsoft.webshop.productservice.model.Attribute;
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
    List<AttributeValueDto> toAttributeValueDtoList(Set<AttributeValue> set);

    @Mapping(target = "label", source = "attributeValue.attribute.label")
    AttributeValueDto toAttributeValueDto(AttributeValue attributeValue);

    default AttributeValue toAttributeValue(ProductAttributeValueCreateDto productAttributeValueCreateDto) {
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setValue(productAttributeValueCreateDto.getValue());
        Attribute attribute = new Attribute();
        attribute.setLabel(productAttributeValueCreateDto.getLabel());
        attribute.addAttributeValue(attributeValue);
        return attributeValue;
    }
}