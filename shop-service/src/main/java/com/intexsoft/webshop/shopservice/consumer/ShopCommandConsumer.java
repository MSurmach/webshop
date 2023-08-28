package com.intexsoft.webshop.shopservice.consumer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderProductCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.OrderRequestToShopCommand;
import org.springframework.messaging.handler.annotation.Payload;

public interface ShopCommandConsumer {
    void receiveCheckOrderShopCommand(CheckOrderShopCommand checkOrderShopCommand);

    void receiveCheckOrderPickupPointCommand(CheckOrderPickupPointCommand checkOrderPickupPointCommand);
    void receiveCheckOrderProductCommand(CheckOrderProductCommand checkOrderProductCommand);
    void receiveOrderRequestToShopCommand(OrderRequestToShopCommand orderRequestToShopCommand);
}