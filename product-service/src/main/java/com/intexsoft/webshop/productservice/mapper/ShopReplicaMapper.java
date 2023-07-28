package com.intexsoft.webshop.productservice.mapper;

import com.intexsoft.webshop.productservice.dto.ShopReplicaDto;
import com.intexsoft.webshop.productservice.model.ShopReplica;
import com.intexsoft.weshop.messagecommon.event.shop.ShopCreatedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopReplicaMapper {

    ShopReplica toShopReplica(ShopReplicaDto shopReplicaDto);

    ShopReplica toShopReplica(ShopCreatedEvent shopCreatedEvent);

    ShopReplicaDto toShopReplicaDto(ShopReplica shopReplica);

    ShopReplicaDto toShopReplicaDto(ShopCreatedEvent shopCreatedEvent);
}