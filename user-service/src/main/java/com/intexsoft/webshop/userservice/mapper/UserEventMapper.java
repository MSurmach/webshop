package com.intexsoft.webshop.userservice.mapper;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserEventMapper {
    @Mapping(target = "orderId", source = "checkOrderUserCommand.orderId")
    @Mapping(target = "userId", source = "checkOrderUserCommand.userId")
    @Mapping(target = "checkResult", source = "checkResult")
    @Mapping(target = "createdAt", ignore = true)
    UserCheckedEvent toUserCheckedEvent(boolean checkResult, CheckOrderUserCommand checkOrderUserCommand);
}