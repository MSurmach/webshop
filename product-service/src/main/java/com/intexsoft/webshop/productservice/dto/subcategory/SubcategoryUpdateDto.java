package com.intexsoft.webshop.productservice.dto.subcategory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productservice.dto.attribute.AttributeUpdateDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubcategoryUpdateDto {
    @Nullable
    @Pattern(regexp = "^(?!\\s*$).+")
    String name;
    @Nullable
    @Pattern(regexp = "^(?!\\s*$).+")
    String description;
    @NotNull
    @Positive
    Long categoryId;
    @Nullable
    @Size(min = 1)
    @JsonProperty("attributes")
    List<AttributeUpdateDto> attributeUpdateDtos;
}