package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeCreateDto;
import com.intexsoft.webshop.productservice.dto.attribute.AttributeUpdateDto;
import com.intexsoft.webshop.productservice.model.Attribute;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
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
}
