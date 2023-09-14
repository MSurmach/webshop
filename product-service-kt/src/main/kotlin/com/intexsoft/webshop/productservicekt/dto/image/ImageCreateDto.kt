package com.intexsoft.webshop.productservicekt.dto.image

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ImageCreateDto(
    @NotBlank
    @Size(max = 512)
    val filePath: String?
)