package com.intexsoft.webshop.shopservice.dto;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ShopDto {
    @Nullable
    @JsonProperty("id")
    Long id;
    @NotBlank(message = "{shop.name.notBlank}")
    @Size(max = 100, message = "{shop.name.size}")
    @JsonProperty("name")
    String name;
    @Email
    @Size(max = 100, message = "{shop.email.size}")
    @JsonProperty("email")
    String email;
    @Nullable
    @Pattern(regexp = "[+]?\\d+", message = "{shop.phoneNumber.pattern}")
    @Size(max = 50, message = "{shop.phoneNumber.size}")
    @JsonProperty("phoneNumber")
    String phoneNumber;
    @Nullable
    String about;
    @Nullable
    @JsonProperty("registeredAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime registeredAt;
    @NotEmpty(message = "{shop.pickupPoints.notEmpty}")
    @JsonProperty("pickupPoints")
    List<PickupPointDto> pickupPointDtos;
}