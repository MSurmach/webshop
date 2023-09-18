package com.intexsoft.webshop.productservicekt.dto.vendor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
@JsonIgnoreProperties(ignoreUnknown = true)
data class VendorCreateDto(
    @field: NotBlank
    @field: Size(min = 3, max = 100)
    val name: String?,
    val about: String?
)