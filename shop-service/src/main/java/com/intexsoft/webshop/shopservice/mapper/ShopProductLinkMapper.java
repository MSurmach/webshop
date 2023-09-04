package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.shopservice.dto.ShopProductLinkCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopProductLinkDto;
import com.intexsoft.webshop.shopservice.model.ProductReplica;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.webshop.shopservice.model.ShopProductLink;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShopProductLinkMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shop", source = "shop")
    @Mapping(target = "product", source = "productReplica")
    @Mapping(target = "price", source = "shopProductLinkCreateDto.price")
    @Mapping(target = "quantity", source = "shopProductLinkCreateDto.quantity")
    ShopProductLink toShopProductLink(Shop shop, ProductReplica productReplica,
                                      ShopProductLinkCreateDto shopProductLinkCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    ShopProductLinkDto toShopProductLinkDto(ShopProductLink shopProductLink);
}