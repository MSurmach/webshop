package com.intexsoft.webshop.orderorchestrator.camunda.delegate;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderorchestrator.mapper.EventToCommandMapper;
import com.intexsoft.webshop.orderorchestrator.producer.OrderOrchestratorCommandProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckProduct implements JavaDelegate {
    private final OrderOrchestratorCommandProducer orderOrchestratorCommandProducer;
    private final EventToCommandMapper eventToCommandMapper;
    @Override
    public void execute(DelegateExecution delegateExecution) {
        OrderInitializedEvent orderInitializedEvent =
                (OrderInitializedEvent) delegateExecution.getVariable("orderInitializedEvent");
        List<Long> orderedProductIds = orderInitializedEvent.getInitOrderProductDetails().stream()
                .map(OrderInitializedEvent.InitOrderProductDetail::getProductId)
                .toList();
        log.info("IN: try to check ordered products with ids in = {} is selling by shop with id = {}",
                orderedProductIds, orderInitializedEvent.getShopId());
        orderOrchestratorCommandProducer.produceCheckOrderProductCommand(
                eventToCommandMapper.toCheckProductCommand(orderInitializedEvent));
    }
}