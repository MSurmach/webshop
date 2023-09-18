package com.intexsoft.webshop.productservicekt.dto.image

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImageCreateDto(
    @field:NotBlank
    @field:Size(max = 512)
    val filePath: String?
)