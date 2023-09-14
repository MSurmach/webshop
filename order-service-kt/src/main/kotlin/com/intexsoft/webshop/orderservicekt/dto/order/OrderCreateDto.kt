package com.intexsoft.webshop.orderservicekt.dto.order

import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.orderservicekt.dto.detail.DetailCreateDto
import com.intexsoft.webshop.orderservicekt.model.enums.PaymentMethod
import jakarta.validation.constraints.Positive

data class OrderCreateDto(
    @Positive
    val pickupPointId: Long,
    @Positive
    val shopId: Long,
    @Positive
    val userId: Long,
    val paymentMethod: PaymentMethod,
    val comment: String?,
    @JsonProperty("orderDetails")
    val detailCreateDtos: List<DetailCreateDto>
)