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

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DecrementProductOrderQuantity implements JavaDelegate {
    private final EventToCommandMapper eventToCommandMapper;
    private final OrderOrchestratorCommandProducer orderOrchestratorCommandProducer;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        OrderInitializedEvent orderInitializedEvent =
                (OrderInitializedEvent) delegateExecution.getVariable("orderInitializedEvent");
        Set<Long> productIds = orderInitializedEvent.getInitOrderProductDetails().stream()
                .map(OrderInitializedEvent.InitOrderProductDetail::getProductId).collect(Collectors.toSet());
        log.info("IN: try to decrement product order quantity for products with ids in = {}", productIds);
        orderOrchestratorCommandProducer.produceChangeProductOrderQuantityCommand(
                eventToCommandMapper.toChangeProductOrderQuantityCommand(
                        orderInitializedEvent,
                        ChangeProductOrderQuantityType.DECREMENT));
    }
}