package com.intexsoft.webshop.orderorchestrator.mapper;

import com.intexsoft.webshop.messagecommon.command.ChangeProductOrderQuantityType;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.*;
import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "orderProductDetails", source = "initOrderProductDetails")
    @Mapping(target = "createdAt", ignore = true)
    CheckOrderProductCommand toCheckProductCommand(OrderInitializedEvent orderInitializedEvent);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "productPrice", target = "productPrice")
    @Mapping(source = "quantity", target = "quantity")
    CheckOrderProductCommand.OrderProductDetail toOrderProductDetail(OrderInitializedEvent.InitOrderProductDetail initOrderProductDetail);

    List<CheckOrderProductCommand.OrderProductDetail> toOrderProductDetailList(List<OrderInitializedEvent.InitOrderProductDetail> initOrderProductDetails);

    @Mapping(target = "changeType", source = "changeProductOrderQuantityType")
    @Mapping(target = "productIdDeltaOrderQuantityMap", source = "orderInitializedEvent",
            qualifiedByName = "getProductIdDeltaOrderQuantityMap")
    ChangeProductOrderQuantityCommand toChangeProductOrderQuantityCommand(OrderInitializedEvent orderInitializedEvent,
                                                                          ChangeProductOrderQuantityType changeProductOrderQuantityType);

    @Named("getProductIdDeltaOrderQuantityMap")
    default Map<Long, Short> getProductIdDeltaOrderQuantityMap(OrderInitializedEvent orderInitializedEvent) {
        return orderInitializedEvent.getInitOrderProductDetails().stream()
                .collect(Collectors.toMap(
                                OrderInitializedEvent.InitOrderProductDetail::getProductId,
                                OrderInitializedEvent.InitOrderProductDetail::getQuantity
                        )
                );
    }

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "shopId", source = "shopId")
    @Mapping(target = "createdAt", ignore = true)
    OrderRequestToShopCommand toOrderRequestToShopCommand(OrderInitializedEvent orderInitializedEvent);
}