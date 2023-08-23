package com.intexsoft.webshop.orderorchestrator.camunda.delegate;

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
public class OrderFailed implements JavaDelegate {
    private final OrderOrchestratorCommandProducer orderOrchestratorCommandProducer;
    private final EventToCommandMapper eventToCommandMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        OrderInitializedEvent orderInitializedEvent =
                (OrderInitializedEvent) delegateExecution.getVariable("orderInitializedEvent");
        log.info("IN: order with id = {} is failed, try to set appropriate status to it", orderInitializedEvent.getOrderId());
        orderOrchestratorCommandProducer.produceFailOrderCommand(
                eventToCommandMapper.toFailOrderCommand(orderInitializedEvent));
    }
}