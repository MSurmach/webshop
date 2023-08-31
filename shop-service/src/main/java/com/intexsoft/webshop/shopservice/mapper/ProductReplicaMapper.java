package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductCreatedEvent;
import com.intexsoft.webshop.shopservice.model.ProductReplica;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductReplicaMapper {
    @Mapping(target = "id", source = "productId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "shopProductLinks", ignore = true)
    ProductReplica toProductReplica(ProductCreatedEvent productCreatedEvent);
}