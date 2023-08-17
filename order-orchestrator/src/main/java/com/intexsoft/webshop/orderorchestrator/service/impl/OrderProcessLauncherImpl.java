package com.intexsoft.webshop.orderorchestrator.service.impl;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;
import com.intexsoft.webshop.orderorchestrator.service.OrderProcessLauncher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProcessLauncherImpl implements OrderProcessLauncher {
    private final RuntimeService runtimeService;
    @Value("${orchestrator.processKey}")
    private String processKey;

    @Override
    public void startOrderProcess(OrderInitializedEvent orderInitializedEvent) {
        log.info("IN: trying to start a new order process, processKey = {}", processKey);
        ProcessInstanceWithVariables orderProcess = runtimeService.createProcessInstanceByKey(processKey)
                .setVariable("orderInitializedEvent", orderInitializedEvent)
                .businessKey(orderInitializedEvent.getOrderId().toString())
                .executeWithVariablesInReturn();
        log.info("OUT: the new order process created successfully." +
                        " ProcessId = {}, businessKey = {}, process variables = {}",
                orderProcess.getProcessInstanceId(), orderProcess.getBusinessKey(), orderProcess.getVariables());
    }
}