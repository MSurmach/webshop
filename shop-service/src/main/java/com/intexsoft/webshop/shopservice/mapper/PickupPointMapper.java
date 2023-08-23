package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.shopservice.dto.PickupPointCreateDto;
import com.intexsoft.webshop.shopservice.dto.PickupPointDto;
import com.intexsoft.webshop.shopservice.model.PickupPoint;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PickupPointMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", source = "address")
    @Mapping(target = "shop", ignore = true)
    PickupPoint toPickupPoint(PickupPointCreateDto pickupPointCreateDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "address", source = "address")
    PickupPointDto toPickupPointDto(PickupPoint pickupPoint);
}