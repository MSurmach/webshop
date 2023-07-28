package com.intexsoft.webshop.feedbackservice.service;

import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;

public interface UserEventConsumer {
    void receiveUserCreatedEvent(UserCreatedEvent userCreatedEvent);
}
