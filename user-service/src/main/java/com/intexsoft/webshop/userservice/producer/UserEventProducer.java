package com.intexsoft.webshop.userservice.producer;

import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCreatedEvent;

public interface UserEventProducer {
    void produceUserCreatedEvent(UserCreatedEvent userCreatedEvent);

    void produceUserCheckedEvent(UserCheckedEvent userCheckedEvent);
}
