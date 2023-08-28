package com.intexsoft.webshop.orderorchestrator.service.impl;

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand;
import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.OrderRequestToShopAddedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ProductCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;
import com.intexsoft.webshop.orderorchestrator.exception.RetryCountExceedException;
import com.intexsoft.webshop.orderorchestrator.producer.OrderOrchestratorCommandProducer;
import com.intexsoft.webshop.orderorchestrator.service.OrderOrchestratorUserTaskProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderOrchestratorUserTaskProcessorImpl implements OrderOrchestratorUserTaskProcessor {
    private final static int MIN_RETRY_COUNT = 1, MAX_RETRY_COUNT = 12, DELAY_RETRY_MS = 3000;

    private final TaskService taskService;
    private final RuntimeService runtimeService;
    private final OrderOrchestratorCommandProducer orderOrchestratorCommandProducer;

    @Value("${orchestrator.waitAndProcessCheckShopResultDefKey}")
    private String waitAndProcessCheckShopResultDefKey;
    @Value("${orchestrator.waitAndProcessCheckPickupPointResultDefKey}")
    private String waitAndProcessCheckPickupPointResultDefKey;
    @Value("${orchestrator.waitAndProcessCheckUserResultDefKey}")
    private String waitAndProcessCheckUserResultDefKey;
    @Value("${orchestrator.waitAndProcessIncrementResultDefKey}")
    private String waitAndProcessIncrementResultDefKey;
    @Value("${orchestrator.waitAndProcessCheckProductResultDefKey}")
    private String waitAndProcessCheckProductResultDefKey;
    @Value("${orchestrator.waitAndProcessOrderRequestResultDefKey}")
    private String waitAndProcessOrderRequestResultDefKey;

    @Override
    public void processAndCompleteCheckShopResult(ShopCheckedEvent shopCheckedEvent) {
        log.info("IN: trying to process and complete task with definition key = {}", waitAndProcessCheckShopResultDefKey);
        String processBusinessKey = shopCheckedEvent.getOrderId().toString();
        boolean isShopValid = shopCheckedEvent.isResult();
        Map<String, Object> variableMap = Map.of("isShopValid", isShopValid);
        try {
            processAndCompleteTaskWithRetry(waitAndProcessCheckShopResultDefKey, processBusinessKey, variableMap);
        } catch (RetryCountExceedException exception) {
            log.error("Impossible to process task, reason: {}", exception.getMessage());
            failProcess(processBusinessKey);
        }
    }

    @Override
    public void processAndCompleteCheckPickupPointResult(PickupPointCheckedEvent pickupPointCheckedEvent) {
        log.info("IN: trying to process task with definition key = {}", waitAndProcessCheckPickupPointResultDefKey);
        boolean isPickupPointValid = pickupPointCheckedEvent.isResult();
        String processBusinessKey = pickupPointCheckedEvent.getOrderId().toString();
        Map<String, Object> variableMap = Map.of("isPickupPointValid", isPickupPointValid);
        try {
            processAndCompleteTaskWithRetry(waitAndProcessCheckPickupPointResultDefKey, processBusinessKey, variableMap);
        } catch (RetryCountExceedException exception) {
            log.error("Impossible to process task, reason: {}", exception.getMessage());
            failProcess(processBusinessKey);
        }
    }

    @Override
    public void processAndCompleteCheckUserResult(UserCheckedEvent userCheckedEvent) {
        log.info("IN: trying to process task with definition key = {}", waitAndProcessCheckUserResultDefKey);
        boolean isUserValid = userCheckedEvent.isCheckResult();
        String processBusinessKey = userCheckedEvent.getOrderId().toString();
        Map<String, Object> variableMap = Map.of("isUserValid", isUserValid);
        try {
            processAndCompleteTaskWithRetry(waitAndProcessCheckUserResultDefKey, processBusinessKey, variableMap);
        } catch (RetryCountExceedException exception) {
            log.error("Impossible to process task, reason: {}", exception.getMessage());
            failProcess(processBusinessKey);
        }
    }

    @Override
    public void processAndCompleteProductOrderQuantityIncrementedResult(ProductOrderQuantityIncrementedEvent productOrderQuantityIncrementedEvent) {
        log.info("IN: trying to process task with definition key = {}", waitAndProcessIncrementResultDefKey);
        boolean isProductOrderQuantityIncremented = productOrderQuantityIncrementedEvent.isResult();
        String processBusinessKey = productOrderQuantityIncrementedEvent.getOrderId().toString();
        Map<String, Object> variableMap = Map.of("isProductOrderQuantityIncremented", isProductOrderQuantityIncremented);
        try {
            processAndCompleteTaskWithRetry(waitAndProcessIncrementResultDefKey, processBusinessKey, variableMap);
        } catch (RetryCountExceedException exception) {
            log.error("Impossible to process task, reason: {}", exception.getMessage());
            failProcess(processBusinessKey);
        }
    }

    @Override
    public void processAndCompleteCheckProductResult(ProductCheckedEvent productCheckedEvent) {
        log.info("IN: trying to process task with definition key = {}", waitAndProcessCheckProductResultDefKey);
        boolean isProductValid = productCheckedEvent.isResult();
        String processBusinessKey = productCheckedEvent.getOrderId().toString();
        Map<String, Object> variableMap = Map.of("isProductValid", isProductValid);
        try {
            processAndCompleteTaskWithRetry(waitAndProcessCheckProductResultDefKey, processBusinessKey, variableMap);
        } catch (RetryCountExceedException exception) {
            log.error("Impossible to process task, reason: {}", exception.getMessage());
            failProcess(processBusinessKey);
        }
    }

    @Override
    public void processAndCompleteOrderRequestResult(OrderRequestToShopAddedEvent orderRequestToShopAddedEvent) {
        log.info("IN: trying to process task with definition key = {}", waitAndProcessOrderRequestResultDefKey);
        boolean isOrderRequested = orderRequestToShopAddedEvent.isResult();
        String processBusinessKey = orderRequestToShopAddedEvent.getOrderId().toString();
        Map<String, Object> variableMap = Map.of("isOrderRequested", isOrderRequested);
        try {
            processAndCompleteTaskWithRetry(waitAndProcessOrderRequestResultDefKey, processBusinessKey, variableMap);
        } catch (RetryCountExceedException exception) {
            log.error("Impossible to process task, reason: {}", exception.getMessage());
            failProcess(processBusinessKey);
        }
    }

    private void processAndCompleteTaskWithRetry(String taskDefKey,
                                                 String processBusinessKey,
                                                 Map<String, Object> variableMap) {
        int retryAttempt = MIN_RETRY_COUNT;
        while (retryAttempt <= MAX_RETRY_COUNT) {
            try {
                completeTaskByDefKeyAndProcessBusinessKey(taskDefKey, processBusinessKey, variableMap);
                return;
            } catch (RuntimeException exception) {
                log.warn("Unable to complete task with definition key = {}. Retry count = {}. Reason is : {}",
                        taskDefKey, retryAttempt, exception.getMessage());
                retryAttempt++;
                try {
                    Thread.sleep(DELAY_RETRY_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (retryAttempt > MAX_RETRY_COUNT)
            throw new RetryCountExceedException();
    }

    private void completeTaskByDefKeyAndProcessBusinessKey(String taskDefKey,
                                                           String processBusinessKey,
                                                           Map<String, Object> variables) {
        log.info("IN: trying to find a task with definition key = {}, that belongs to process with business key = {}",
                taskDefKey, processBusinessKey);
        Task task = Optional.ofNullable(
                        taskService.createTaskQuery()
                                .processInstanceBusinessKey(processBusinessKey)
                                .taskDefinitionKey(taskDefKey)
                                .singleResult())
                .orElseThrow(() -> new NullPointerException("Task with definition key = " + taskDefKey + " is null"));
        log.info("OUT: the task with name = {} and definition key = {} FOUND", task.getName(),
                task.getTaskDefinitionKey());
        log.info("OUT: trying to complete task with definition key = {}, variables will be assigned = {}",
                taskDefKey, variables);
        taskService.complete(task.getId(), variables);
    }

    private void failProcess(String processBusinessKey) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(processBusinessKey)
                .singleResult();
        runtimeService.suspendProcessInstanceById(processInstance.getProcessInstanceId());
        FailOrderCommand failOrderCommand = new FailOrderCommand();
        failOrderCommand.setOrderId(Long.parseLong(processBusinessKey));
        orderOrchestratorCommandProducer.produceFailOrderCommand(failOrderCommand);
    }
}