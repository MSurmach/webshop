package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent;
import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import com.intexsoft.webshop.productservice.model.AttributeValue;
import com.intexsoft.webshop.productservice.model.Product;
import com.intexsoft.webshop.productservice.model.Subcategory;
import com.intexsoft.webshop.productservice.model.Vendor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {SubcategoryMapper.class, VendorMapper.class, ImageMapper.class, AttributeValueMapper.class})
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "productCreateDto.name")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "images", source = "productCreateDto.imageCreateDtos")
    @Mapping(target = "attributeValues", source = "attributeValues")
    Product toProduct(ProductCreateDto productCreateDto, Vendor vendor,
                      Subcategory subcategory, List<AttributeValue> attributeValues);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "subcategoryDto", source = "subcategory")
    @Mapping(target = "vendorDto", source = "vendor")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "attributeValueDtos", source = "attributeValues")
    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> productList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "productUpdateDto.name")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeValues",ignore = true)
    Product updateProduct(@MappingTarget Product product, ProductUpdateDto productUpdateDto, Vendor vendor,
                          Subcategory subcategory);
}