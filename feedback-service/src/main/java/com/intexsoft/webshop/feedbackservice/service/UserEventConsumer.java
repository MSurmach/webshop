package com.intexsoft.webshop.feedbackservice.service;

import com.intexsoft.weshop.messagecommon.event.user.UserCreatedEvent;

public interface UserEventConsumer {
    void receive(UserCreatedEvent userCreatedEvent);
}
