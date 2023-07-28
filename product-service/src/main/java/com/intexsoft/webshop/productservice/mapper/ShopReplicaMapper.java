package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.messagecommon.event.shop.ShopCreatedEvent;
import com.intexsoft.webshop.productservice.dto.ShopReplicaDto;
import com.intexsoft.webshop.productservice.model.ShopReplica;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopReplicaMapper {

    ShopReplica toShopReplica(ShopReplicaDto shopReplicaDto);

    @Mapping(source = "shopId", target = "id")
    ShopReplica toShopReplica(ShopCreatedEvent shopCreatedEvent);

    ShopReplicaDto toShopReplicaDto(ShopReplica shopReplica);

    @Mapping(source = "shopId", target = "id")
    ShopReplicaDto toShopReplicaDto(ShopCreatedEvent shopCreatedEvent);
}