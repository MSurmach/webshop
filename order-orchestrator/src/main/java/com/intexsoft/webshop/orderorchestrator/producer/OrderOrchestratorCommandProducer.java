package com.intexsoft.webshop.orderorchestrator.producer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderPickupPointCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderShopCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;

public interface OrderOrchestratorCommandProducer {
    void produceCheckOrderPickupPointCommand(CheckOrderPickupPointCommand checkOrderPickupPointCommand);

    void produceCheckOrderShopCommand(CheckOrderShopCommand checkOrderShopCommand);

    void produceCheckOrderUserCommand(CheckOrderUserCommand checkOrderUserCommand);

    void produceFailOrderCommand(FailOrderCommand failOrderCommand);
}