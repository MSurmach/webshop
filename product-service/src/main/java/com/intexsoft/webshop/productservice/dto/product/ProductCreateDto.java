package com.intexsoft.webshop.productservice.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueCreateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageCreateDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class ProductCreateDto {
    @NotBlank
    @Size(max = 255)
    String name;
    @NotNull
    @Positive
    Long subcategoryId;
    @NotNull
    @Positive
    Long vendorId;
    @Nullable
    @Size(min = 1)
    @JsonProperty("images")
    List<ImageCreateDto> imageCreateDtos;
    @Nullable
    @Size(min = 1)
    @JsonProperty("attributeValues")
    List<AttributeValueCreateDto> attributeValueCreateDtos;
}