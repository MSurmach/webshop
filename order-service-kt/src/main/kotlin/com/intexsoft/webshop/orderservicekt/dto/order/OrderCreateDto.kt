package com.intexsoft.webshop.orderservicekt.dto.order

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.intexsoft.webshop.orderservicekt.dto.detail.DetailCreateDto
import com.intexsoft.webshop.orderservicekt.model.enums.PaymentMethod
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
@JsonIgnoreProperties(ignoreUnknown = true)
data class OrderCreateDto(
    @field:Positive
    val pickupPointId: Long,
    @field:Positive
    val shopId: Long,
    @field:Positive
    val userId: Long,
    val paymentMethod: PaymentMethod,
    val comment: String?,
    @JsonProperty("orderDetails")
    @field:NotEmpty
    @field:Valid
    val detailCreateDtos: List<DetailCreateDto>
)