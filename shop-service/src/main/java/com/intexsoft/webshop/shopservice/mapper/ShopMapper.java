package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCreatedEvent;
import com.intexsoft.webshop.shopservice.dto.ShopCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;
import com.intexsoft.webshop.shopservice.model.Shop;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = PickupPointMapper.class)
public interface ShopMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "registeredAt", ignore = true)
    @Mapping(target = "about", source = "about")
    @Mapping(target = "shopProductLinks", ignore = true)
    @Mapping(target = "pickupPoints", source = "pickupPointCreateDtos")
    Shop toShop(ShopCreateDto shopCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "about", source = "about")
    @Mapping(target = "registeredAt", source = "registeredAt")
    @Mapping(target = "pickupPointDtos", source = "pickupPoints")
    ShopDto toShopDto(Shop shop);

    @Mapping(target = "shopId", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "createdAt", ignore = true)
    ShopCreatedEvent toShopCreatedEvent(Shop shop);
}