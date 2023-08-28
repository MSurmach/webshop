package com.intexsoft.webshop.orderorchestrator.camunda.delegate;

import com.intexsoft.webshop.messagecommon.command.ChangeProductOrderQuantityType;
import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderorchestrator.mapper.EventToCommandMapper;
import com.intexsoft.webshop.orderorchestrator.producer.OrderOrchestratorCommandProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRequestToShop implements JavaDelegate {
    private final EventToCommandMapper eventToCommandMapper;
    private final OrderOrchestratorCommandProducer orderOrchestratorCommandProducer;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        OrderInitializedEvent orderInitializedEvent =
                (OrderInitializedEvent) delegateExecution.getVariable("orderInitializedEvent");
        log.info("IN: try to request order with id = {} to shop with id = {}",
                orderInitializedEvent.getOrderId(), orderInitializedEvent.getShopId());
        orderOrchestratorCommandProducer.produceOrderRequestToShopCommand(
                eventToCommandMapper.toOrderRequestToShopCommand(orderInitializedEvent));
    }
}