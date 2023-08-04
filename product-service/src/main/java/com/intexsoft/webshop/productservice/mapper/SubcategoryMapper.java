package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import com.intexsoft.webshop.productservice.model.Category;
import com.intexsoft.webshop.productservice.model.Subcategory;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = AttributeMapper.class)
public interface SubcategoryMapper {

    @Mapping(target = "name", source = "subcategoryCreateDto.name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attributes", source = "subcategoryCreateDto.attributeCreateDtos")
    Subcategory toSubcategory(SubcategoryCreateDto subcategoryCreateDto, Category category);

    @Mapping(target = "attributes", source = "attributeUpdateDtos")
    Subcategory updateSubcategory(@MappingTarget Subcategory subcategory, SubcategoryUpdateDto subcategoryUpdateDto);

    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "attributeDtos", source = "attributes")
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);

    List<SubcategoryDto> toSubcategoryDtos(List<Subcategory> subcategories);
}