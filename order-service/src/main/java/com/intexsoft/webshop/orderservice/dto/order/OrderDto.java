package com.intexsoft.webshop.orderservice.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.orderservice.dto.detail.DetailDto;
import com.intexsoft.webshop.orderservice.dto.status.StatusDto;
import com.intexsoft.webshop.orderservice.model.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    Long id;
    Long pickupPointId;
    Long shopId;
    Long userId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime createdAt;
    BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    String comment;
    @JsonProperty("statuses")
    List<StatusDto> statusDtos;
    @JsonProperty("details")
    List<DetailDto> orderDetailDtos;
}