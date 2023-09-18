package com.intexsoft.webshop.productservicekt.dto.attribute

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class AttributeUpdateDto(
    @field:Positive
    val id: Long?,
    @field:NotBlank
    @field:Size(max = 50)
    val label: String?
)