package com.intexsoft.webshop.productservice.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueUpdateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageUpdateDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;
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
public class ProductUpdateDto {
    @Nullable
    @Size(max = 255)
    @Pattern(regexp = "^(?!\\s*$).+")
    String name;
    @Nullable
    @Positive
    Long subcategoryId;
    @Nullable
    @Positive
    Long vendorId;
    @Nullable
    @Size(min = 1)
    @JsonProperty("images")
    List<ImageUpdateDto> imageUpdateDtos;
    @Nullable
    @Size(min = 1)
    @JsonProperty("attributeValues")
    List<AttributeValueUpdateDto> attributeValueUpdateDtos;
}