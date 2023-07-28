package com.intexsoft.webshop.shopservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopCreateDto {
    @NotBlank(message = "{shop.name.notBlank}")
    @Size(max = 100, message = "{shop.name.size}")
    String name;
    @Email
    @Size(max = 100, message = "{shop.email.size}")
    String email;
    @Nullable
    @Pattern(regexp = "[+]?\\d+", message = "{shop.phoneNumber.pattern}")
    @Size(max = 50, message = "{shop.phoneNumber.size}")
    String phoneNumber;
    @Nullable
    String about;
    @NotEmpty(message = "{shop.pickupPoints.notEmpty}")
    @JsonProperty("pickupPoints")
    List<PickupPointCreateDto> pickupPointCreateDtos;
}
