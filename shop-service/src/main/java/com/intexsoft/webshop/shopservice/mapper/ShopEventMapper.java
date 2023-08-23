package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShopEventMapper {
    @Mapping(target = "orderId", source = "checkOrderShopCommand.orderId")
    @Mapping(target = "shopId", source = "checkOrderShopCommand.shopId")
    @Mapping(target = "checkResult", source = "checkResult")
    @Mapping(target = "createdAt", ignore = true)
    ShopCheckedEvent toShopCheckedEvent(boolean checkResult,
                                        CheckOrderShopCommand checkOrderShopCommand);

    @Mapping(target = "orderId", source = "checkOrderPickupPointCommand.orderId")
    @Mapping(target = "shopId", source = "checkOrderPickupPointCommand.shopId")
    @Mapping(target = "pickupPointId", source = "checkOrderPickupPointCommand.pickupPointId")
    @Mapping(target = "checkResult", source = "checkResult")
    @Mapping(target = "createdAt", ignore = true)
    PickupPointCheckedEvent toPickupPointCheckedEvent(boolean checkResult,
                                                      CheckOrderPickupPointCommand checkOrderPickupPointCommand);
}
