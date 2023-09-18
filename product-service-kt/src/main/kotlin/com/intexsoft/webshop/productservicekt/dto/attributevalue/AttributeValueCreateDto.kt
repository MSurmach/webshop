package com.intexsoft.webshop.productservicekt.dto.attributevalue

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttributeValueCreateDto(
    @field:Positive
    val attributeId: Long,
    @field:NotBlank
    @field:Size(max = 50)
    val value: String?
)
