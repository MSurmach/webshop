package com.intexsoft.webshop.orderorchestrator.service;

import com.intexsoft.webshop.messagecommon.event.order.OrderInitializedEvent;

public interface OrderProcessLauncher {
    void startOrderProcess(OrderInitializedEvent orderInitializedEvent);
}