package com.intexsoft.webshop.messagecommon.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEvent {
    @JsonProperty("createdAt")
    LocalDateTime createdAt;

    protected BaseEvent() {
        createdAt = LocalDateTime.now();
    }
}
