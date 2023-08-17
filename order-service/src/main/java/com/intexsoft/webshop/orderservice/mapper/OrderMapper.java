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
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "orderDetails", source = "orderCreateDto.detailCreateDtos")
    @Mapping(target = "statuses", source = "status")
    Order toOrder(OrderCreateDto orderCreateDto, Status status, BigDecimal totalPrice);

    @Mapping(target = "statusDtos", source = "statuses")
    @Mapping(target = "orderDetailDtos", source = "orderDetails")
    OrderDto toOrderDto(Order order);

    @Mapping(target = "orderId", source = "id")
    OrderInitializedEvent toOrderInitializedEvent(Order order);
}