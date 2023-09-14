package com.intexsoft.webshop.productservicekt.dto.product

import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueCreateDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageCreateDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ProductCreateDto(
    @NotBlank
    @Size(max = 255)
    val name: String,
    @Positive
    val subcategoryId: Long,
    @Positive
    val vendorId: Long,
    @JsonProperty("images")
    @Size(min = 1)
    val imageCreateDtos: MutableList<ImageCreateDto>?,
    @JsonProperty("attributeValues")
    @Size(min = 1)
    val attributeValueCreateDtos: MutableList<AttributeValueCreateDto>?
)