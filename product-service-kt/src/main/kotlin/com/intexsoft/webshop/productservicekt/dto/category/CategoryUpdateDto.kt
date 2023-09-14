package com.intexsoft.webshop.productservicekt.dto.category

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CategoryUpdateDto(
    @NotBlank
    @Size(max = 255)
    val name: String
)