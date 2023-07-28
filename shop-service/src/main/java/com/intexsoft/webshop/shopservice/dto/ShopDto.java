package com.intexsoft.webshop.shopservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopDto {
    Long id;
    String name;
    String email;
    String phoneNumber;
    @Nullable
    String about;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime registeredAt;
    @JsonProperty("pickupPoints")
    List<PickupPointDto> pickupPointDtos;
}