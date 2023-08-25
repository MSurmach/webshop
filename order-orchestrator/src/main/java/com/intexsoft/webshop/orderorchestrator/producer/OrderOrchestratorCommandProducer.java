package com.intexsoft.webshop.orderorchestrator.producer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.*;

public interface OrderOrchestratorCommandProducer {
    void produceCheckOrderPickupPointCommand(CheckOrderPickupPointCommand checkOrderPickupPointCommand);

    void produceCheckOrderShopCommand(CheckOrderShopCommand checkOrderShopCommand);

    void produceCheckOrderUserCommand(CheckOrderUserCommand checkOrderUserCommand);

    void produceFailOrderCommand(FailOrderCommand failOrderCommand);

    void produceCheckOrderProductCommand(CheckOrderProductCommand checkOrderProductCommand);

    void produceChangeProductOrderQuantityCommand(ChangeProductOrderQuantityCommand changeProductOrderQuantityCommand);
    void produceOrderRequestToShopCommand(OrderRequestToShopCommand orderRequestToShopCommand);
}