package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueDto
import com.intexsoft.webshop.productservicekt.model.AttributeValue
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface AttributeValueMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "attributeId", source = "attributeValue.attribute.id")
    fun attributeValueToAttributeValueDto(attributeValue: AttributeValue): AttributeValueDto
}