package com.intexsoft.webshop.eventbus.service;

import com.intexsoft.webshop.eventbus.model.Event;
import com.intexsoft.webshop.messagecommon.BaseEvent;

public interface EventService {
    Event saveEvent(BaseEvent baseEvent);
}
