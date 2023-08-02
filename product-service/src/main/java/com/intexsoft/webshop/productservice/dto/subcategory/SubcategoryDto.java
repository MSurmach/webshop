package com.intexsoft.webshop.productservice.dto.subcategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubcategoryDto {
    Long id;
    String name;
    String description;
    @JsonProperty("category")
    CategoryDto categoryDto;
}