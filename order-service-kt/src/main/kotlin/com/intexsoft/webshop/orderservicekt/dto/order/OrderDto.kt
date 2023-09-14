package com.intexsoft.webshop.orderservicekt.dto.order

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.orderservicekt.dto.detail.DetailDto
import com.intexsoft.webshop.orderservicekt.dto.status.StatusDto
import com.intexsoft.webshop.orderservicekt.model.enums.PaymentMethod
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderDto(
    val id: Long,
    val pickupPointId: Long,
    val shopId: Long,
    val userId: Long,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    val createdAt: LocalDateTime,
    val totalPrice: BigDecimal,
    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,
    val comment: String,
    @JsonProperty("statuses")
    val statusDtos: List<StatusDto>,
    @JsonProperty("details")
    val orderDetailDtos: List<DetailDto>
)