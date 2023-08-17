package com.intexsoft.webshop.orderorchestrator.producer;

import com.intexsoft.webshop.messagecommon.command.BaseCommand;
import com.intexsoft.webshop.orderorchestrator.enums.CommandType;
import com.intexsoft.webshop.orderorchestrator.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public abstract class AbstractOrderOrchestratorCommandProducer implements OrderOrchestratorCommandProducer {
    private RabbitTemplate rabbitTemplate;

    public AbstractOrderOrchestratorCommandProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    protected String exchange;
    protected String routingPrefix;

    @Override
    public void convertAndSendCommand(BaseCommand baseCommand, CommandType commandType) {
        log.info("IN: try to produce a command = {}", JsonUtils.getAsString(baseCommand));
        String routing = routingPrefix + commandType.lowerCaseName();
        rabbitTemplate.convertAndSend(exchange, routing, baseCommand);
        log.debug("The command is producing to exchange = {}, with routing = {}", exchange, routing);
    }
}
