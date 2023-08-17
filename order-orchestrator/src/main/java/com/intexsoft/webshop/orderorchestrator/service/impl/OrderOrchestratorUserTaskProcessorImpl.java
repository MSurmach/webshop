package com.intexsoft.webshop.orderorchestrator.service.impl;

import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;
import com.intexsoft.webshop.orderorchestrator.service.OrderOrchestratorUserTaskProcessor;
import com.intexsoft.webshop.orderorchestrator.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        log.info("IN: the new message {} received. Message body = {}",
                shopCheckedEvent.getClass().getName(), JsonUtils.getAsString(shopCheckedEvent));
        boolean isShopValid = shopCheckedEvent.isCheckResult();
        Task foundTask = findTaskByDefKeyAndProcessBusinessKey(
                waitAndProcessCheckShopResultDefKey,
                shopCheckedEvent.getOrderId().toString());
        taskService.complete(foundTask.getId(), Map.of("isShopValid", isShopValid));
    }

    @Override
    public void processAndCompleteCheckPickupPointResult(PickupPointCheckedEvent pickupPointCheckedEvent) {
        log.info("IN: the new message {} received. Message body = {}",
                pickupPointCheckedEvent.getClass().getName(), JsonUtils.getAsString(pickupPointCheckedEvent));
        boolean isPickupPointValid = pickupPointCheckedEvent.isCheckResult();
        Task foundTask = findTaskByDefKeyAndProcessBusinessKey(
                waitAndProcessCheckPickupPointResultDefKey,
                pickupPointCheckedEvent.getOrderId().toString());
        taskService.complete(foundTask.getId(), Map.of("isPickupPointValid", isPickupPointValid));
    }

    @Override
    public void processAndCompleteCheckUserResult(UserCheckedEvent userCheckedEvent) {
        log.info("IN: the new message {} received. Message body = {}",
                userCheckedEvent.getClass().getName(), JsonUtils.getAsString(userCheckedEvent));
        boolean isUserValid = userCheckedEvent.isCheckResult();
        Task foundTask = findTaskByDefKeyAndProcessBusinessKey(
                waitAndProcessCheckUserResultDefKey,
                userCheckedEvent.getOrderId().toString());
        taskService.complete(foundTask.getId(), Map.of("isUserValid", isUserValid));
    }

    private Task findTaskByDefKeyAndProcessBusinessKey(String taskDefKey, String processBusinessKey) {
        return taskService.createTaskQuery()
                .processInstanceBusinessKey(processBusinessKey)
                .processDefinitionKey(taskDefKey).
                singleResult();
    }
}
