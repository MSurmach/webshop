package com.intexsoft.webshop.orderservice.consumer.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;
import com.intexsoft.webshop.orderservice.consumer.OrderCommandConsumer;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import com.intexsoft.webshop.orderservice.service.StatusService;
import com.intexsoft.webshop.orderservice.util.JsonUtils;
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
public class OrderCommandConsumerImpl implements OrderCommandConsumer {
    private final StatusService statusService;

    @RabbitHandler
    @Override
    public void receiveFailOrderCommand(@Payload FailOrderCommand failOrderCommand) {
        log.info("New event message {} received. Message payload = {}",
                failOrderCommand.getClass().getName(), JsonUtils.getAsString(failOrderCommand));
        statusService.addStatusToOrderWithId(failOrderCommand.getOrderId(), StatusName.FAILED);
    }
}
