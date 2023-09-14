package com.intexsoft.webshop.productservicekt.dto.subcategory

import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeCreateDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive

data class SubcategoryCreateDto(
    @NotBlank
    val name: String,
    @NotBlank
    val description: String,
    @Positive
    val categoryId: Long,
    @JsonProperty("attributes")
    @NotEmpty
    val attributeCreateDtos: MutableList<AttributeCreateDto>
)
