package com.intexsoft.webshop.orderorchestrator.producer;

import com.intexsoft.webshop.messagecommon.command.BaseCommand;
import com.intexsoft.webshop.orderorchestrator.enums.CommandType;
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
        log.info("IN: try to produce a message command = {}", commandType);
        String routing = routingPrefix + commandType.lowerCaseName();
        log.info("OUT: the command = {} is producing to exchange = {}, with routing = {}", commandType, exchange, routing);
        rabbitTemplate.convertAndSend(exchange, routing, baseCommand);
    }
}