package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.shopservice.dto.PickupPointDto;
import com.intexsoft.webshop.shopservice.dto.ShopApiExceptionDto;
import com.intexsoft.webshop.shopservice.dto.ShopDto;
import com.intexsoft.webshop.shopservice.exception.SuchShopExistsException;
import com.intexsoft.webshop.shopservice.model.PickupPoint;
import com.intexsoft.webshop.shopservice.model.Shop;
import com.intexsoft.weshop.messagecommon.event.shop.ShopCreatedEvent;
import org.mapstruct.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ShopApiMapper {

    @Mapping(target = "registeredAt", ignore = true)
    @Mapping(target = "pickupPoints", source = "pickupPointDtos")
    Shop toShop(ShopDto shopDto);

    @Mapping(target = "shop", ignore = true)
    PickupPoint toPickupPoint(PickupPointDto pickupPointDto);

    @Mapping(target = "pickupPointDtos", source = "pickupPoints")
    ShopDto toShopDto(Shop shop);

    PickupPointDto toPickupPointDto(PickupPoint pickupPoint);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ShopCreatedEvent toShopCreatedEvent(Shop shop);

    default ShopApiExceptionDto toShopApiExceptionDto(MethodArgumentNotValidException exception) {
        return ShopApiExceptionDto.builder()
                .exceptionMessage(exception.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("; ")))
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    default ShopApiExceptionDto toShopApiExceptionDto(SuchShopExistsException exception) {
        return ShopApiExceptionDto.builder()
                .exceptionMessage(exception.getMessage())
                .exceptionTimestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build();
    }
}