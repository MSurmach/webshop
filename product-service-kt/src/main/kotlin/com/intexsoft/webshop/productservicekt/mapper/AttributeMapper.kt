package com.intexsoft.webshop.productservicekt.mapper;

import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeCreateDto
import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeDto
import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeUpdateDto
import com.intexsoft.webshop.productservicekt.model.Attribute
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface AttributeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "label", source = "label")
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    fun toAttribute(attributeCreateDto: AttributeCreateDto): Attribute

    @Mapping(target = "id", source = "id")
    @Mapping(target = "label", source = "label")
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    fun toAttribute(attributeUpdateDto: AttributeUpdateDto): Attribute

    @Mapping(target = "id", source = "id")
    @Mapping(target = "label", source = "label")
    fun toAttributeDto(attribute: Attribute): AttributeDto

    fun toAttributeDtoList(attributes: MutableSet<Attribute>): MutableList<AttributeDto>
}