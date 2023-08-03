package com.intexsoft.webshop.productservice.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueDto;
import com.intexsoft.webshop.productservice.dto.image.ImageDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
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
}
