package com.intexsoft.webshop.messagecommon.command;

import com.intexsoft.webshop.messagecommon.BaseEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class OrderCommand extends BaseEvent {
    Long orderId;
}
