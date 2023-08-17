package com.intexsoft.webshop.orderorchestrator.camunda.delegate;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderorchestrator.enums.CommandType;
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
public class CheckShop implements JavaDelegate {

    private final OrderOrchestratorCommandProducer orderOrchestratorCommandToShopProducer;
    private final EventToCommandMapper eventToCommandMapper;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        OrderInitializedEvent orderInitializedEvent =
                (OrderInitializedEvent) delegateExecution.getVariable("orderInitializedEvent");
        log.info("IN: try to check shop with id = {}", orderInitializedEvent.getShopId());
        orderOrchestratorCommandToShopProducer.convertAndSendCommand(
                eventToCommandMapper.toCheckShopCommand(orderInitializedEvent),
                CommandType.CHECK_SHOP);
    }
}