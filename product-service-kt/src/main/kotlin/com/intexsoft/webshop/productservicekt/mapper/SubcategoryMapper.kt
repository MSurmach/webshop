package com.intexsoft.webshop.productservicekt.mapper

import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryUpdateDto
import com.intexsoft.webshop.productservicekt.model.Category
import com.intexsoft.webshop.productservicekt.model.Subcategory
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    uses = [AttributeMapper::class, CategoryMapper::class]
)
interface SubcategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "subcategoryCreateDto.name")
    @Mapping(target = "description", source = "subcategoryCreateDto.description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "attributes", source = "subcategoryCreateDto.attributeCreateDtos")
    @Mapping(target = "products", ignore = true)
    fun toSubcategory(subcategoryCreateDto: SubcategoryCreateDto, category: Category): Subcategory

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "subcategoryUpdateDto.name")
    @Mapping(target = "description", source = "subcategoryUpdateDto.description")
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "attributes", source = "attributeUpdateDtos")
    @Mapping(target = "products", ignore = true)
    fun updateSubcategory(
        @MappingTarget subcategory: Subcategory,
        subcategoryUpdateDto: SubcategoryUpdateDto
    ): Subcategory

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    fun toSubcategoryDto(subcategory: Subcategory): SubcategoryDto
    fun toSubcategoryDtos(subcategories: List<Subcategory>): List<SubcategoryDto>
}