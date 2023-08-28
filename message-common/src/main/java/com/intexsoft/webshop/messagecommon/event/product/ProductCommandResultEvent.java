package com.intexsoft.webshop.messagecommon.event.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ProductCommandResultEvent extends BatchProductEvent {
    boolean result;
    Long orderId;
}