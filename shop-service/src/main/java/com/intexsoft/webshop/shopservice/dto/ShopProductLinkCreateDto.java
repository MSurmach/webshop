package com.intexsoft.webshop.shopservice.dto;

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
public class ShopProductLinkCreateDto {
    @Positive
    @NotNull
    Long productId;
    @Positive
    @NotNull
    Long shopId;
    @Positive
    @NotNull
    BigDecimal price;
    @Positive
    @NotNull
    Short quantity;
}