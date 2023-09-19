package com.intexsoft.webshop.eventbus.service.impl;

import com.intexsoft.webshop.eventbus.model.Event;
import com.intexsoft.webshop.eventbus.repository.EventRepository;
import com.intexsoft.webshop.eventbus.service.EventService;
import com.intexsoft.webshop.eventbus.util.JsonUtils;
import com.intexsoft.webshop.messagecommon.BaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Event saveEvent(BaseEvent baseEvent) {
        String eventAsString = JsonUtils.getAsString(baseEvent);
        log.info("Trying to save a new event = {}", eventAsString);
        Event savedEvent = eventRepository.save(
                Event.builder()
                        .type(baseEvent.getClass().getSimpleName())
                        .createdAt(baseEvent.getCreatedAt())
                        .jsonContent(eventAsString)
                        .build());
        log.info("New event saved successfully, assigned id = {}", savedEvent.getEventId());
        return savedEvent;
    }
}
