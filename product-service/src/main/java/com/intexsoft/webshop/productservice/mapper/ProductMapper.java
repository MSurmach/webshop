package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.messagecommon.event.product.ProductCreatedEvent;
import com.intexsoft.webshop.messagecommon.event.product.ProductDeletedEvent;
import com.intexsoft.webshop.messagecommon.event.product.ProductUpdatedEvent;
import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {SubcategoryMapper.class, VendorMapper.class, ImageMapper.class, AttributeValueMapper.class})
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "productCreateDto.name")
    @Mapping(target = "vendor", source = "vendorDto")
    @Mapping(target = "subcategory", source = "subcategoryDto")
    @Mapping(target = "images", source = "productCreateDto.imageCreateDtos")
    @Mapping(target = "attributeValues", source = "productCreateDto.productAttributeValueCreateDtos")
    Product toProduct(ProductCreateDto productCreateDto, VendorDto vendorDto, SubcategoryDto subcategoryDto);

    @Mapping(target = "subcategoryDto", source = "subcategory")
    @Mapping(target = "vendorDto", source = "vendor")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "attributeValueDtos", source = "attributeValues")
    ProductDto toProductDto(Product product);

    ProductCreatedEvent toProductEventCreated(Product product);

    ProductDeletedEvent toProductEventDeleted(Long productId);

    List<ProductDto> toProductDtos(List<Product> productList);

    Product updateProduct(@MappingTarget Product product, ProductUpdateDto productUpdateDto);

    ProductUpdatedEvent toProductEventUpdated(Product product);
}