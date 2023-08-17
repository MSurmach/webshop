package com.intexsoft.webshop.orderservice.dto.detail;

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
public class DetailDto {
    Long id;
    Long productId;
    BigDecimal productPrice;
    Short quantity;
}
