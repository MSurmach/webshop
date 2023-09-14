package com.intexsoft.webshop.productservicekt.dto.attributevalue

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class AttributeValueUpdateDto(
    @Positive
    val attributeId: Long,
    @Pattern(regexp = "^(?!\\s*$).+")
    @Size(max = 50)
    val value: String?
)