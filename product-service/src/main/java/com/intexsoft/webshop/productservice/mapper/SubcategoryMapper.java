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
        uses = {AttributeMapper.class, CategoryMapper.class})
public interface SubcategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "subcategoryCreateDto.name")
    @Mapping(target = "description", source = "subcategoryCreateDto.description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "attributes", source = "subcategoryCreateDto.attributeCreateDtos")
    @Mapping(target = "products", ignore = true)
    Subcategory toSubcategory(SubcategoryCreateDto subcategoryCreateDto, Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "subcategoryUpdateDto.name")
    @Mapping(target = "description", source = "subcategoryUpdateDto.description")
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "attributes", source = "attributeUpdateDtos")
    @Mapping(target = "products", ignore = true)
    Subcategory updateSubcategory(@MappingTarget Subcategory subcategory, SubcategoryUpdateDto subcategoryUpdateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "attributeDtos", source = "attributes")
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);

    List<SubcategoryDto> toSubcategoryDtos(List<Subcategory> subcategories);
}