package com.intexsoft.webshop.productservicekt.dto.product

import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueUpdateDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageUpdateDto
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ProductUpdateDto(
    @Size(max = 255)
    @Pattern(regexp = "^(?!\\s*$).+")
    val name: String?,
    @Positive
    val subcategoryId: Long?,
    @Positive
    val vendorId: Long?,
    @JsonProperty("images")
    @Size(min = 1)
    val imageUpdateDtos: MutableList<ImageUpdateDto>?,
    @JsonProperty("attributeValues")
    @Size(min = 1)
    val attributeValueUpdateDtos: MutableList<AttributeValueUpdateDto>?
)