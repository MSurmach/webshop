package com.intexsoft.webshop.shopservice.mapper;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderProductCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.OrderRequestToShopCommand;
import com.intexsoft.webshop.messagecommon.event.shop.impl.*;
import com.intexsoft.webshop.shopservice.model.ShopProductLink;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ShopEventMapper {
    @Mapping(target = "orderId", source = "checkOrderShopCommand.orderId")
    @Mapping(target = "shopId", source = "checkOrderShopCommand.shopId")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "createdAt", ignore = true)
    ShopCheckedEvent toShopCheckedEvent(boolean result,
                                        CheckOrderShopCommand checkOrderShopCommand);

    @Mapping(target = "orderId", source = "checkOrderPickupPointCommand.orderId")
    @Mapping(target = "shopId", source = "checkOrderPickupPointCommand.shopId")
    @Mapping(target = "pickupPointId", source = "checkOrderPickupPointCommand.pickupPointId")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "createdAt", ignore = true)
    PickupPointCheckedEvent toPickupPointCheckedEvent(boolean result,
                                                      CheckOrderPickupPointCommand checkOrderPickupPointCommand);

    @Mapping(target = "orderId", source = "checkOrderProductCommand.orderId")
    @Mapping(target = "shopId", source = "checkOrderProductCommand.shopId")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "productIds", source = "checkOrderProductCommand.orderProductDetails", qualifiedByName = "getProductIds")
    ProductCheckedEvent toOrderProductCheckedEvent(boolean result, CheckOrderProductCommand checkOrderProductCommand);

    @Mapping(target = "orderId", source = "orderRequestToShopCommand.orderId")
    @Mapping(target = "shopId", source = "orderRequestToShopCommand.shopId")
    @Mapping(target = "result", source = "result")
    @Mapping(target = "createdAt", ignore = true)
    OrderRequestToShopAddedEvent toOrderRequestToShopAddedEvent(boolean result, OrderRequestToShopCommand orderRequestToShopCommand);

    @Named("getProductIds")
    default Set<Long> getProductIds(List<CheckOrderProductCommand.OrderProductDetail> orderProductDetails) {
        return orderProductDetails.stream()
                .map(CheckOrderProductCommand.OrderProductDetail::getProductId)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "shopId", source = "shop.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "createdAt", ignore = true)
    ShopProductLinkCreatedEvent toShopProductLinkCreatedEvent(ShopProductLink shopProductLink);
}
