package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import com.intexsoft.webshop.productservice.model.Subcategory;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubcategoryMapper {

    @Mapping(target = "category", source = "categoryDto")
    @Mapping(target = "name", source = "subcategoryCreateDto.name")
    @Mapping(target = "id", ignore = true)
    Subcategory toSubcategory(SubcategoryCreateDto subcategoryCreateDto, CategoryDto categoryDto);

    Subcategory updateSubcategory(@MappingTarget Subcategory subcategory, SubcategoryUpdateDto subcategoryUpdateDto);

    @Mapping(target = "categoryDto", source = "category")
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);

    @Mapping(target = "category", ignore = true)
    Subcategory toSubcategory(SubcategoryDto subcategoryDto);

    List<SubcategoryDto> toSubcategoryDtos(List<Subcategory> subcategories);
}