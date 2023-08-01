package com.intexsoft.webshop.userservice.service;

import com.intexsoft.webshop.messagecommon.event.user.UserCreatedEvent;

public interface UserEventProducer {
    void produceUserCreatedEvent(UserCreatedEvent userCreatedEvent);
}
