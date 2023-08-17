package com.intexsoft.webshop.orderorchestrator.producer;

import com.intexsoft.webshop.messagecommon.command.BaseCommand;
import com.intexsoft.webshop.orderorchestrator.enums.CommandType;

public interface OrderOrchestratorCommandProducer {
    void convertAndSendCommand(BaseCommand baseCommand, CommandType commandType);
}