package com.intexsoft.webshop.productservicekt.dto.attributevalue

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttributeValueUpdateDto(
    @field:Positive
    val attributeId: Long,
    @field:Pattern(regexp = "^(?!\\s*$).+")
    @field:Size(max = 50)
    val value: String?
)