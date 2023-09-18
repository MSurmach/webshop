package com.intexsoft.webshop.productservicekt.dto.vendor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
@JsonIgnoreProperties(ignoreUnknown = true)
data class VendorUpdateDto(
    @field:Size(max=100)
    @field:Pattern(regexp = "^(?!\\s*$).+")
    val name: String?,
    @field:Pattern(regexp = "^(?!\\s*$).+")
    val about: String?
)
