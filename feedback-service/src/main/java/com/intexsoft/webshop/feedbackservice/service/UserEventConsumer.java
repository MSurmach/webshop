package com.intexsoft.webshop.feedbackservice.service;

import com.intexsoft.webshop.messagecommon.event.user.UserCreatedEvent;

public interface UserEventConsumer {
    void receiveUserCreatedEvent(UserCreatedEvent userCreatedEvent);
}
