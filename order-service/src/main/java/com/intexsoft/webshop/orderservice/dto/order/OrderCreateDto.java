package com.intexsoft.webshop.orderservice.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.orderservice.dto.detail.DetailCreateDto;
import com.intexsoft.webshop.orderservice.model.enums.PaymentMethod;
import com.intexsoft.webshop.orderservice.validation.ValueOfEnum;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCreateDto {
    @NotNull
    @Positive
    Long pickupPointId;
    @NotNull
    @Positive
    Long shopId;
    @NotNull
    @Positive
    Long userId;
    @NotBlank
    @ValueOfEnum(enumClass = PaymentMethod.class)
    String paymentMethod;
    @Nullable
    @Pattern(regexp = "^(?!\\s*$).+")
    String comment;
    @NotEmpty
    @JsonProperty("orderDetails")
    List<DetailCreateDto> detailCreateDtos;
}