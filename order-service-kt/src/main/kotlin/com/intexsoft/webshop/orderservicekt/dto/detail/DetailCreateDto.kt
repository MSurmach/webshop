package com.intexsoft.webshop.orderservicekt.dto.detail

import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class DetailCreateDto(
    @field:Positive
    val productId: Long,
    @field:Positive
    val productPrice: BigDecimal,
    @field:Positive
    val quantity: Short
)