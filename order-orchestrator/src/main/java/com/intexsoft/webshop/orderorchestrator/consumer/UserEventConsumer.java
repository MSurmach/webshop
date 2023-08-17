package com.intexsoft.webshop.orderorchestrator.consumer;

import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;

public interface UserEventConsumer {
    void receiveUserCheckedEvent(UserCheckedEvent userCheckedEvent);
}
