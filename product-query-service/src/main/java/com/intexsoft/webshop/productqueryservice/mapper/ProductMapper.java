package com.intexsoft.webshop.productqueryservice.mapper;

import com.intexsoft.webshop.productqueryservice.dto.ProductDto;
import com.intexsoft.webshop.productqueryservice.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productName", source = "productName")
    @Mapping(target = "subcategoryDto", source = "subcategory")
    @Mapping(target = "vendorDto", source = "vendor")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "attributeValueDtos", source = "attributeValues")
    ProductDto toProductDto(Product product);

    @Mapping(target = "vendorId", source = "vendorId")
    @Mapping(target = "vendorName", source = "vendorName")
    @Mapping(target = "about", source = "about")
    ProductDto.VendorDto toVendorDto(Product.Vendor vendor);

    @Mapping(target = "subcategoryId", source = "subcategoryId")
    @Mapping(target = "subcategoryName", source = "subcategoryName")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "attributeDtos", source = "attributes")
    ProductDto.SubcategoryDto toSubcategoryDto(Product.Subcategory subcategory);

    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "categoryName", source = "categoryName")
    ProductDto.SubcategoryDto.CategoryDto toCategoryDto(Product.Subcategory.Category category);

    @Mapping(target = "attributeId", source = "attributeId")
    @Mapping(target = "label", source = "label")
    ProductDto.SubcategoryDto.AttributeDto toAttributeDto(Product.Subcategory.Attribute attribute);

    @Mapping(target = "imageId", source = "imageId")
    @Mapping(target = "filePath", source = "filePath")
    ProductDto.ImageDto toImageDto(Product.Image image);

    @Mapping(target = "attributeValueId", source = "attributeValueId")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "attributeId", source = "attributeId")
    ProductDto.AttributeValueDto toAttributeValueDto(Product.AttributeValue attributeValue);

    List<ProductDto> toProductDtos(List<Product> products);
}