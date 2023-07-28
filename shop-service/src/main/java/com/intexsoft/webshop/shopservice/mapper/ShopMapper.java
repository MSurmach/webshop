package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.shopservice.dto.ShopCreateDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.weshop.messagecommon.event.shop.ShopCreatedEvent;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = PickupPointMapper.class)
public interface ShopMapper {

    @Mapping(target = "registeredAt", ignore = true)
    @Mapping(target = "pickupPoints", source = "pickupPointCreateDtos")
    Shop toShop(ShopCreateDto shopCreateDto);

    @Mapping(target = "pickupPointDtos", source = "pickupPoints")
    ShopDto toShopDto(Shop shop);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ShopCreatedEvent toShopCreatedEvent(Shop shop);
}