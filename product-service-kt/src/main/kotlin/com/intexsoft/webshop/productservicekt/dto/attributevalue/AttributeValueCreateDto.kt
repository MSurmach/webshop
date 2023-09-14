package com.intexsoft.webshop.productservicekt.dto.attributevalue

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class AttributeValueCreateDto(
    @Positive
    val attributeId: Long,
    @NotBlank
    @Size(max = 50)
    val value: String
)
