package com.intexsoft.webshop.productservicekt.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueUpdateDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageUpdateDto
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductUpdateDto(
    @field:Size(max = 255)
    @field:Pattern(regexp = "^(?!\\s*$).+")
    val name: String?,
    @field:Positive
    val subcategoryId: Long?,
    @field:Positive
    val vendorId: Long?,
    @JsonProperty("images")
    @field:Valid
    @field:Size(min = 1)
    val imageUpdateDtos: List<ImageUpdateDto>?,
    @JsonProperty("attributeValues")
    @field:Valid
    @field:Size(min = 1)
    val attributeValueUpdateDtos: List<AttributeValueUpdateDto>?
)