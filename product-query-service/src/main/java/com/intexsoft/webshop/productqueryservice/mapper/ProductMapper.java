package com.intexsoft.webshop.productqueryservice.mapper;

import com.intexsoft.webshop.productqueryservice.dto.ProductDto;
import com.intexsoft.webshop.productqueryservice.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "vendorDto", source = "vendor")
    @Mapping(target = "subcategoryDto", source = "subcategory")
    @Mapping(target = "subcategoryDto.categoryDto", source = "subcategory.category")
    @Mapping(target = "subcategoryDto.attributeDtos", source = "subcategory.attributes")
    @Mapping(target = "imageDtos", source = "images")
    @Mapping(target = "attributeValueDtos", source = "attributeValues")
    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> products);
}