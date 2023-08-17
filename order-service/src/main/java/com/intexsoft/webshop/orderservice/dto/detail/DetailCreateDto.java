package com.intexsoft.webshop.orderservice.dto.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailCreateDto {
    @NotNull
    @Positive
    Long productId;
    @NotNull
    @Positive
    BigDecimal productPrice;
    @NotNull
    @Positive
    Short quantity;
}