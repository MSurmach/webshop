package com.intexsoft.webshop.messagecommon.event.order;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInitializedEvent extends OrderEvent {
    Long pickupPointId;
    Long shopId;
    Long userId;
}