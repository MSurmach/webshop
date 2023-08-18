package com.intexsoft.webshop.orderorchestrator.service.impl;

import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;
import com.intexsoft.webshop.orderorchestrator.service.OrderOrchestratorUserTaskProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderOrchestratorUserTaskProcessorImpl implements OrderOrchestratorUserTaskProcessor {
    private final TaskService taskService;
    @Value("${orchestrator.waitAndProcessCheckShopResultDefKey}")
    private String waitAndProcessCheckShopResultDefKey;
    @Value("${orchestrator.waitAndProcessCheckPickupPointResultDefKey}")
    private String waitAndProcessCheckPickupPointResultDefKey;
    @Value("${orchestrator.waitAndProcessCheckUserResultDefKey}")
    private String waitAndProcessCheckUserResultDefKey;

    @Override
    public void processAndCompleteCheckShopResult(ShopCheckedEvent shopCheckedEvent) {
        log.info("IN: trying to process and complete task with definition key = {}",
                waitAndProcessCheckShopResultDefKey);
        boolean isShopValid = shopCheckedEvent.isCheckResult();
        Task foundTask = findTaskByDefKeyAndProcessBusinessKey(
                waitAndProcessCheckShopResultDefKey,
                shopCheckedEvent.getOrderId().toString());
        Map<String, Object> variableMap = Map.of("isShopValid", isShopValid);
        log.info("OUT: trying to complete task with definition key = {}, variables will be assigned = {}",
                foundTask.getTaskDefinitionKey(), variableMap);
        taskService.complete(foundTask.getId(), variableMap);
    }

    @Override
    public void processAndCompleteCheckPickupPointResult(PickupPointCheckedEvent pickupPointCheckedEvent) {
        log.info("IN: trying to process task with definition key = {}",
                waitAndProcessCheckPickupPointResultDefKey);
        boolean isPickupPointValid = pickupPointCheckedEvent.isCheckResult();
        Task foundTask = findTaskByDefKeyAndProcessBusinessKey(
                waitAndProcessCheckPickupPointResultDefKey,
                pickupPointCheckedEvent.getOrderId().toString());
        Map<String, Object> variableMap = Map.of("isPickupPointValid", isPickupPointValid);
        log.info("OUT: trying to complete task with definition key = {}, variables will be assigned = {}",
                foundTask.getTaskDefinitionKey(), variableMap);
        taskService.complete(foundTask.getId(), variableMap);
    }

    @Override
    public void processAndCompleteCheckUserResult(UserCheckedEvent userCheckedEvent) {
        log.info("IN: trying to process task with definition key = {}",
                waitAndProcessCheckUserResultDefKey);
        boolean isUserValid = userCheckedEvent.isCheckResult();
        Task foundTask = findTaskByDefKeyAndProcessBusinessKey(
                waitAndProcessCheckUserResultDefKey,
                userCheckedEvent.getOrderId().toString());
        Map<String, Object> variableMap = Map.of("isUserValid", isUserValid);
        log.info("OUT: trying to complete task with definition key = {}, variables will be assigned = {}",
                foundTask.getTaskDefinitionKey(), variableMap);
        taskService.complete(foundTask.getId(), variableMap);
    }

    private Task findTaskByDefKeyAndProcessBusinessKey(String taskDefKey, String processBusinessKey) {
        log.info("IN: trying to find a task with definition key = {}, that belongs to process with business key = {}",
                taskDefKey, processBusinessKey);
        TaskQuery taskQuery = taskService.createTaskQuery()
                .processInstanceBusinessKey(processBusinessKey)
                .taskDefinitionKey(taskDefKey);
        Task task;
        while (Objects.isNull(task = taskQuery.singleResult())) {
        }
        log.info("OUT: the task with name = {} and definition key = {} FOUND", task.getName(),
                task.getTaskDefinitionKey());
        return task;
    }
}