package com.intexsoft.webshop.orderservice.consumer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;

public interface OrderCommandConsumer {
    void receiveFailOrderCommand(FailOrderCommand failOrderCommand);
}