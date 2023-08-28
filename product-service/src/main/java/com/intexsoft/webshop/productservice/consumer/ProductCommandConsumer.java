package com.intexsoft.webshop.productservice.consumer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.ChangeProductOrderQuantityCommand;

public interface ProductCommandConsumer {
    void receiveChangeProductOrderQuantityCommand(ChangeProductOrderQuantityCommand changeProductOrderQuantityCommand);
}