package com.intexsoft.webshop.productservicekt.dto.attribute

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AttributeUpdateDto(
    @Positive
    val id: Long?,
    @NotBlank
    @Size(max = 50)
    val label: String
)