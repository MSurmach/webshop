package com.intexsoft.webshop.productservicekt.dto.vendor

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class VendorCreateDto(
    @NotBlank
    @Size(max = 100)
    val name: String,
    val about: String?
)