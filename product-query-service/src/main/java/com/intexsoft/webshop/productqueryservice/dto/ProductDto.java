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
    Long productId;
    String productName;
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
        Long subcategoryId;
        String subcategoryName;
        String description;
        @JsonProperty("category")
        CategoryDto categoryDto;
        @JsonProperty("attributes")
        List<AttributeDto> attributeDtos;

        @Getter
        @Setter
        public static class CategoryDto {
            Long categoryId;
            String categoryName;
        }

        @Getter
        @Setter
        public static class AttributeDto {
            Long attributeId;
            String label;
        }
    }

    @Getter
    @Setter
    public static class VendorDto {
        Long vendorId;
        String vendorName;
        String about;
    }

    @Getter
    @Setter
    public static class AttributeValueDto {
        Long attributeValueId;
        String value;
        Long attributeId;
    }

    @Getter
    @Setter
    public static class ImageDto {
        Long imageId;
        String filePath;
    }
}