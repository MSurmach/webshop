package com.intexsoft.webshop.productqueryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    @JsonProperty("subcategory")
    SubcategoryDto subcategoryDto;
    @JsonProperty("vendor")
    VendorDto vendorDto;
    @JsonProperty("images")
    List<ImageDto> imageDtos;
    @JsonProperty("attributeValues")
    List<AttributeValueDto> attributeValueDtos;

    @Getter
    @Setter
    public static class SubcategoryDto {
        Long id;
        String name;
        String description;
        @JsonProperty("category")
        CategoryDto categoryDto;
        @JsonProperty("attributes")
        List<AttributeDto> attributeDtos;

        @Getter
        @Setter
        public static class CategoryDto {
            Long id;
            String name;
        }

        @Getter
        @Setter
        public static class AttributeDto {
            Long id;
            String label;
        }
    }

    @Getter
    @Setter
    public static class VendorDto {
        Long id;
        String name;
        String about;
    }

    @Getter
    @Setter
    public static class AttributeValueDto {
        Long id;
        String value;
        Long attributeId;
    }

    @Getter
    @Setter
    public static class ImageDto {
        Long id;
        String filePath;
    }
}