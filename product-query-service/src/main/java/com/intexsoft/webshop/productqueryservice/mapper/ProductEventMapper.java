package com.intexsoft.webshop.productqueryservice.mapper;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.productqueryservice.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductEventMapper {
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "productName", source = "name")
    @Mapping(target = "vendor", source = "vendor")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "images", source = "images")
    @Mapping(target = "attributeValues", source = "attributeValues")
    Product toProduct(ProductCreatedEvent productCreatedEvent);

    @Mapping(target = "vendorId", source = "id")
    @Mapping(target = "vendorName", source = "name")
    @Mapping(target = "about", source = "about")
    Product.Vendor toVendor(ProductCreatedEvent.Vendor vendor);

    @Mapping(target = "subcategoryId", source = "id")
    @Mapping(target = "subcategoryName", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "attributes", source = "attributes")
    Product.Subcategory toSubcategory(ProductCreatedEvent.Subcategory subcategory);

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    Product.Subcategory.Category toCategory(ProductCreatedEvent.Subcategory.Category category);
    @Mapping(target = "attributeId", source = "id")
    @Mapping(target = "label", source = "label")
    Product.Subcategory.Attribute toAttribute(ProductCreatedEvent.Subcategory.Attribute attribute);
    @Mapping(target = "imageId", source = "id")
    @Mapping(target = "filePath", source = "filePath")
    Product.Image toImage(ProductCreatedEvent.Image image);
    @Mapping(target = "attributeValueId", source = "id")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "attributeId", source = "attributeId")
    Product.AttributeValue toAttributeValue(ProductCreatedEvent.AttributeValue attributeValue);
}