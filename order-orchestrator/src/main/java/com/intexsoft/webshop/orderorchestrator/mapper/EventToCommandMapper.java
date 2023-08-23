package com.intexsoft.webshop.orderorchestrator.mapper;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;
import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventToCommandMapper {
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    @Mapping(target = "createdAt", ignore = true)
    CheckOrderPickupPointCommand toCheckPickupPointCommand(OrderInitializedEvent orderInitializedEvent);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "createdAt", ignore = true)
    CheckOrderShopCommand toCheckShopCommand(OrderInitializedEvent orderInitializedEvent);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "createdAt", ignore = true)
    CheckOrderUserCommand toCheckUserCommand(OrderInitializedEvent orderInitializedEvent);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "createdAt", ignore = true)
    FailOrderCommand toFailOrderCommand(OrderInitializedEvent orderInitializedEvent);
}