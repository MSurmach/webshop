package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeDto;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueCreateDto;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueDto;
import com.intexsoft.webshop.productservice.model.Attribute;
import com.intexsoft.webshop.productservice.model.AttributeValue;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AttributeValueMapper {
    List<AttributeDto> toAttributeDtoList(Set<AttributeValue> attributeValueSet);

    AttributeDto ToAttributeDto(AttributeValue attributeValue);

    AttributeValueDto toAttributeValueDto(AttributeValue attributeValue);

    @Named("toAttributeValues")
    default Set<AttributeValue> toAttributeValues(List<AttributeValueCreateDto> attributeValueCreateDtos,
                                                  List<AttributeDto> attributeDtos) {
        Map<Long, String> attributeIdLabelMap = attributeDtos.stream()
                .collect(Collectors.toMap(AttributeDto::getId, AttributeDto::getLabel));
        return attributeValueCreateDtos.stream()
                .map(attributeValueCreateDto -> {
                    Attribute attribute = new Attribute();
                    attribute.setId(attributeValueCreateDto.getAttributeId());
                    attribute.setLabel(attributeIdLabelMap.get(attributeValueCreateDto.getAttributeId()));
                    AttributeValue attributeValue = new AttributeValue();
                    attributeValue.setValue(attributeValueCreateDto.getValue());
                    attributeValue.setAttribute(attribute);
                    return attributeValue;
                })
                .collect(Collectors.toSet());
    }
}