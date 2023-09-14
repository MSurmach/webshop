package com.intexsoft.webshop.productservicekt.dto.vendor

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class VendorUpdateDto(
    @Size(max=100)
    @Pattern(regexp = "^(?!\\s*$).+")
    val name: String?,
    @Pattern(regexp = "^(?!\\s*$).+")
    val about: String?
)
