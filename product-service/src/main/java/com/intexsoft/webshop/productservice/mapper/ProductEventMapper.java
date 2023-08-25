package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductDeletedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductUpdatedEvent;
import com.intexsoft.webshop.productservice.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductEventMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "createdAt", ignore = true)
    ProductCreatedEvent toProductEventCreated(Product product);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "createdAt", ignore = true)
    ProductDeletedEvent toProductEventDeleted(Long productId);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "productId", source = "id")
    @Mapping(target = "createdAt", ignore = true)
    ProductUpdatedEvent toProductEventUpdated(Product product);

    @Mapping(target = "result", source = "result")
    @Mapping(target = "productIds", source = "productIds")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "createdAt", ignore = true)
    ProductOrderQuantityIncrementedEvent toProductOrderQuantityIncrementedEvent(Set<Long> productIds, boolean result, Long orderId);
}