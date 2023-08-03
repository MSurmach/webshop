package com.intexsoft.webshop.productservice.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueUpdateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageCreateDto;
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
public class ProductUpdateDto {
    @NotBlank
    @Size(max = 255)
    String name;
    @NotNull
    @Positive
    Long subcategoryId;
    @NotNull
    @Positive
    Long vendorId;
    @NotEmpty
    @JsonProperty("images")
    List<ImageCreateDto> imageCreateDtos;
    @NotEmpty
    @JsonProperty("attributeValues")
    List<AttributeValueUpdateDto> attributeValueUpdateDtos;
}