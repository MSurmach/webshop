package com.intexsoft.webshop.shopservice.service;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;

public interface ShopCheckerService {

    void checkShop(CheckOrderShopCommand checkOrderShopCommand);

    void checkPickupPoint(CheckOrderPickupPointCommand checkOrderPickupPointCommand);
}