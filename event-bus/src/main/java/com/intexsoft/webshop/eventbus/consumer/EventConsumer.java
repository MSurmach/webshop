package com.intexsoft.webshop.eventbus.consumer;

import com.intexsoft.webshop.messagecommon.BaseEvent;

public interface EventConsumer {

    void receiveEvent(BaseEvent baseEvent);
}
