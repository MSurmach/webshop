package com.intexsoft.webshop.productservicekt.dto.subcategory

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeCreateDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
@JsonIgnoreProperties(ignoreUnknown = true)
data class SubcategoryCreateDto(
    @field:NotBlank
    val name: String?,
    @field:NotBlank
    val description: String?,
    @field:Positive
    val categoryId: Long,
    @JsonProperty("attributes")
    @field:Valid
    @field:NotEmpty
    val attributeCreateDtos: List<AttributeCreateDto>
)
