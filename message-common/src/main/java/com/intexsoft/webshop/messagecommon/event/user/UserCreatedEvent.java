package com.intexsoft.webshop.messagecommon.event.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreatedEvent extends UserEvent {
    String login;
}
