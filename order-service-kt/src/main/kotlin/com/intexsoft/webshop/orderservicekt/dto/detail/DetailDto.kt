package com.intexsoft.webshop.orderservicekt.dto.detail

import java.math.BigDecimal

data class DetailDto(
    val id: Long,
    val productId: Long,
    val productPrice: BigDecimal,
    val quantity: Short
)
