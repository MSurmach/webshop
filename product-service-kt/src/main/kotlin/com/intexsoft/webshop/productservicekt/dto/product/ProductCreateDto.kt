package com.intexsoft.webshop.productservicekt.dto.product

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueCreateDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageCreateDto
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductCreateDto(
    @field:NotBlank
    @field:Size(max = 255)
    val name: String?,
    @field:Positive
    val subcategoryId: Long,
    @field:Positive
    val vendorId: Long,
    @JsonProperty("images")
    @field:Valid
    @field:Size(min = 1)
    val imageCreateDtos: List<ImageCreateDto>?,
    @JsonProperty("attributeValues")
    @field:Valid
    @field:Size(min = 1)
    val attributeValueCreateDtos: List<AttributeValueCreateDto>?
)