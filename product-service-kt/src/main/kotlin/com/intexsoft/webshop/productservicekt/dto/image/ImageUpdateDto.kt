package com.intexsoft.webshop.productservicekt.dto.image

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ImageUpdateDto(
    @Positive
    val id: Long?,
    @NotBlank
    @Size(max = 512)
    val filePath: String?
)