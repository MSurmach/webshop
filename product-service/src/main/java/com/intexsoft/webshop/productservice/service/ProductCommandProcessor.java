package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.ChangeProductOrderQuantityCommand;

public interface ProductCommandProcessor {
    void processChangeProductOrderQuantityCommand(ChangeProductOrderQuantityCommand changeProductOrderQuantityCommand);
}