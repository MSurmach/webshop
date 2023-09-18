package com.intexsoft.webshop.productservicekt.dto.category

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonIgnoreProperties(ignoreUnknown = true)
data class CategoryCreateDto(
    @field:NotBlank
    @field:Size(max = 255)
    val name: String?
)