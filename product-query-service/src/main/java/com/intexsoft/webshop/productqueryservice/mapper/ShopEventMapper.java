package com.intexsoft.webshop.productqueryservice.mapper;

import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopProductLinkCreatedEvent;
import com.intexsoft.webshop.productqueryservice.model.ShopProductLink;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShopEventMapper {
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "shopName", source = "shopName")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    ShopProductLink toShopProductLink(ShopProductLinkCreatedEvent shopProductLinkCreatedEvent);
}