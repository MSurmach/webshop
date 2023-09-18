package com.intexsoft.webshop.productservicekt.dto.image

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImageUpdateDto(
    @field:Positive
    val id: Long?,
    @field:NotBlank
    @field:Size(max = 512)
    val filePath: String?
)