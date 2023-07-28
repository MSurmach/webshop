package com.intexsoft.webshop.shopservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PickupPointCreateDto {
    @NotBlank(message = "{shop.pickupPoint.address.notBlank}")
    @Size(max = 512, message = "{shop.pickupPoint.address.size}")
    @JsonProperty("address")
    String address;
}