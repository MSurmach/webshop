package com.intexsoft.webshop.productservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.ChangeProductOrderQuantityCommand;
import com.intexsoft.webshop.productservice.consumer.ProductCommandConsumer;
import com.intexsoft.webshop.productservice.service.ProductCommandProcessor;
import com.intexsoft.webshop.productservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.ExchangeTypes.TOPIC;

@Service
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.command.queue}"),
        exchange = @Exchange(name = "${rmq.command.exchange}", type = TOPIC),
        key = "${rmq.command.routing}")
)
@Slf4j
public class ProductCommandConsumerImpl implements ProductCommandConsumer {
    private final ProductCommandProcessor productCommandProcessor;

    @Override
    @RabbitHandler
    public void receiveChangeProductOrderQuantityCommand(@Payload ChangeProductOrderQuantityCommand changeProductOrderQuantityCommand) {
        log.info("New event message {} received. Message payload = {}",
                changeProductOrderQuantityCommand.getClass().getSimpleName(), JsonUtils.getAsString(changeProductOrderQuantityCommand));
        productCommandProcessor.processChangeProductOrderQuantityCommand(changeProductOrderQuantityCommand);
    }
}