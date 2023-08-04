package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeCreateDto;
import com.intexsoft.webshop.productservice.dto.attribute.AttributeUpdateDto;
import com.intexsoft.webshop.productservice.model.Attribute;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AttributeMapper {

    Attribute toAttribute(AttributeCreateDto attributeCreateDto);

    Attribute toAttribute(AttributeUpdateDto attributeUpdateDto);
}
