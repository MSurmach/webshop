package com.intexsoft.webshop.productservicekt.dto.subcategory

import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeUpdateDto
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class SubcategoryUpdateDto(
    @Pattern(regexp = "^(?!\\s*$).+")
    val name: String?,
    @Pattern(regexp = "^(?!\\s*$).+")
    val description: String?,
    @Positive
    val categoryId: Long,
    @JsonProperty("attributes")
    @Size(min = 1)
    val attributeUpdateDtos: MutableList<AttributeUpdateDto>?
)
