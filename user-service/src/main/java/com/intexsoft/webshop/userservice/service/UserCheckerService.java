package com.intexsoft.webshop.userservice.service;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.CheckOrderUserCommand;

public interface UserCheckerService {

    void checkUser(CheckOrderUserCommand checkOrderUserCommand);

}