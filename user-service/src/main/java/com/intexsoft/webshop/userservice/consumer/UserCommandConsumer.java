package com.intexsoft.webshop.userservice.consumer;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;

public interface UserCommandConsumer {
    void receiveCheckOrderUserCommand(CheckOrderUserCommand checkOrderUserCommand);

}
