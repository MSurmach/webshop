package com.intexsoft.webshop.orderservicekt.mapper

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent
import com.intexsoft.webshop.orderservicekt.dto.order.OrderCreateDto
import com.intexsoft.webshop.orderservicekt.dto.order.OrderDto
import com.intexsoft.webshop.orderservicekt.model.Order
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import java.math.BigDecimal

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = [DetailMapper::class, StatusMapper::class]
)
interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pickupPointId", source = "orderCreateDto.pickupPointId")
    @Mapping(target = "shopId", source = "orderCreateDto.shopId")
    @Mapping(target = "userId", source = "orderCreateDto.userId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "paymentMethod", source = "orderCreateDto.paymentMethod")
    @Mapping(target = "comment", source = "orderCreateDto.comment")
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "statuses", ignore = true)
    fun toOrder(orderCreateDto: OrderCreateDto, totalPrice: BigDecimal): Order

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
    fun toOrderDto(order: Order): OrderDto

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "initOrderProductDetails", source = "orderDetails")
    fun toOrderInitializedEvent(order: Order): OrderInitializedEvent
}