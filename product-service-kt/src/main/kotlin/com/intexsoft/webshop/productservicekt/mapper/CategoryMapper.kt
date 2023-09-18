package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.productservicekt.dto.category.CategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryUpdateDto
import com.intexsoft.webshop.productservicekt.model.Category
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "subcategories", ignore = true)
    fun toCategory(categoryCreateDto: CategoryCreateDto): Category

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    fun toCategoryDto(category: Category): CategoryDto
    fun toCategoryDtos(categories: List<Category>): List<CategoryDto>

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "categoryUpdateDto.name")
    @Mapping(target = "subcategories", ignore = true)
    fun updateCategory(@MappingTarget category: Category, categoryUpdateDto: CategoryUpdateDto): Category
}