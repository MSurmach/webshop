package com.intexsoft.webshop.orderservicekt.dto.detail

import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class DetailCreateDto(
    @Positive
    val productId: Long,
    @Positive
    val productPrice: BigDecimal,
    @Positive
    val quantity: Short
)