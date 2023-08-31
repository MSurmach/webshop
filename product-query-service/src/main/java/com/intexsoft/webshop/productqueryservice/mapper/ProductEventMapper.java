package com.intexsoft.webshop.productqueryservice.mapper;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.productqueryservice.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductEventMapper {
    @Mapping(target = "id", source = "productId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "subcategory.category", source = "subcategory.category")
    @Mapping(target = "subcategory.attributes", source = "subcategory.attributes")
    @Mapping(target = "images", source = "images")
    @Mapping(target = "attributeValues", source = "attributeValues")
    Product toProduct(ProductCreatedEvent productCreatedEvent);
}