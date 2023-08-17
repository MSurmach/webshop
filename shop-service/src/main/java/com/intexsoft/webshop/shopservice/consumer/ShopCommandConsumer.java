package com.intexsoft.webshop.shopservice.consumer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;

public interface ShopCommandConsumer {
    void receiveCheckOrderShopCommand(CheckOrderShopCommand checkOrderShopCommand);

    void receiveCheckOrderPickupPointCommand(CheckOrderPickupPointCommand checkOrderPickupPointCommand);
}
