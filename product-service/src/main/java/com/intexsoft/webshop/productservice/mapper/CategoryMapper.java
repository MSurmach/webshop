package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.category.CategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryUpdateDto;
import com.intexsoft.webshop.productservice.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "subcategories", ignore = true)
    Category toCategory(CategoryCreateDto categoryCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtos(List<Category> categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "categoryUpdateDto.name")
    @Mapping(target = "subcategories", ignore = true)
    Category updateCategory(@MappingTarget Category category, CategoryUpdateDto categoryUpdateDto);
}