package com.intexsoft.webshop.orderservice.mapper;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderservice.dto.order.OrderCreateDto;
import com.intexsoft.webshop.orderservice.dto.order.OrderDto;
import com.intexsoft.webshop.orderservice.model.Order;
import com.intexsoft.webshop.orderservice.model.Status;
import org.mapstruct.*;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = DetailMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pickupPointId", source = "orderCreateDto.pickupPointId")
    @Mapping(target = "shopId", source = "orderCreateDto.shopId")
    @Mapping(target = "userId", source = "orderCreateDto.userId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "paymentMethod", source = "orderCreateDto.paymentMethod")
    @Mapping(target = "comment", source = "orderCreateDto.comment")
    @Mapping(target = "orderDetails", source = "orderCreateDto.detailCreateDtos")
    @Mapping(target = "statuses", source = "status")
    Order toOrder(OrderCreateDto orderCreateDto, Status status, BigDecimal totalPrice);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    @Mapping(target = "comment", source = "comment")
    @Mapping(target = "statusDtos", source = "statuses")
    @Mapping(target = "orderDetailDtos", source = "orderDetails")
    OrderDto toOrderDto(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "userId", source = "userId")
    OrderInitializedEvent toOrderInitializedEvent(Order order);
}