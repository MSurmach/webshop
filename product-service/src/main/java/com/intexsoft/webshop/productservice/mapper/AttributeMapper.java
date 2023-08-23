package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeCreateDto;
import com.intexsoft.webshop.productservice.dto.attribute.AttributeDto;
import com.intexsoft.webshop.productservice.dto.attribute.AttributeUpdateDto;
import com.intexsoft.webshop.productservice.model.Attribute;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AttributeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "label", source = "label")
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    Attribute toAttribute(AttributeCreateDto attributeCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "label", source = "label")
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "attributeValues", ignore = true)
    Attribute toAttribute(AttributeUpdateDto attributeUpdateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "label", source = "label")
    AttributeDto toAttributeDto(Attribute attribute);

    List<AttributeDto> toAttributeDtoList(Set<Attribute> attributes);
}