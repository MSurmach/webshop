package com.intexsoft.webshop.orderorchestrator.consumer.impl;

import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;
import com.intexsoft.webshop.orderorchestrator.consumer.UserEventConsumer;
import com.intexsoft.webshop.orderorchestrator.service.OrderOrchestratorUserTaskProcessor;
import com.intexsoft.webshop.orderorchestrator.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "${rmq.event.user.queue}"),
        exchange = @Exchange(name = "${rmq.event.user.exchange}", type = ExchangeTypes.TOPIC),
        key = "${rmq.event.user.routing}")
)
@Slf4j
public class UserEventConsumerImpl implements UserEventConsumer {
    private final OrderOrchestratorUserTaskProcessor orderOrchestratorUserTaskProcessor;

    @RabbitHandler
    @Override
    public void receiveUserCheckedEvent(@Payload UserCheckedEvent userCheckedEvent) {
        log.info("New event message {} received. Message payload = {}",
                userCheckedEvent.getClass().getSimpleName(), JsonUtils.getAsString(userCheckedEvent));
        orderOrchestratorUserTaskProcessor.processAndCompleteCheckUserResult(userCheckedEvent);
    }
}