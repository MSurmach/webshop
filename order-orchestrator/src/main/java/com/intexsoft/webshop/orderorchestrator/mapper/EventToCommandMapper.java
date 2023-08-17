package com.intexsoft.webshop.orderorchestrator.mapper;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;
import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventToCommandMapper {
    CheckOrderPickupPointCommand toCheckPickupPointCommand(OrderInitializedEvent orderInitializedEvent);

    CheckOrderShopCommand toCheckShopCommand(OrderInitializedEvent orderInitializedEvent);

    CheckOrderUserCommand toCheckUserCommand(OrderInitializedEvent orderInitializedEvent);

    FailOrderCommand toFailOrderCommand(OrderInitializedEvent orderInitializedEvent);
}
