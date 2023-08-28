package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderProductCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.OrderRequestToShopCommand;

public interface ShopCommandProcessor {

    void checkShop(CheckOrderShopCommand checkOrderShopCommand);

    void checkPickupPoint(CheckOrderPickupPointCommand checkOrderPickupPointCommand);

    void checkProduct(CheckOrderProductCommand checkOrderProductCommand);

    void addOrderRequest(OrderRequestToShopCommand orderRequestToShopCommand);
}