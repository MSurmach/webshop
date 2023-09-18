package com.intexsoft.webshop.productservicekt.dto.subcategory

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attribute.AttributeUpdateDto
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class SubcategoryUpdateDto(
    @field:Pattern(regexp = "^(?!\\s*$).+")
    val name: String?,
    @field:Pattern(regexp = "^(?!\\s*$).+")
    val description: String?,
    @field:Positive
    val categoryId: Long,
    @JsonProperty("attributes")
    @field:Valid
    @field:Size(min = 1)
    val attributeUpdateDtos: List<AttributeUpdateDto>?
)
