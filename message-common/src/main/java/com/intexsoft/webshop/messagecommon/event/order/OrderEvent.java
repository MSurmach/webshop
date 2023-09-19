package com.intexsoft.webshop.messagecommon.event.order;

import com.intexsoft.webshop.messagecommon.BaseEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class OrderEvent extends BaseEvent {
    Long orderId;
}
