package com.intexsoft.webshop.messagecommon.event.shop;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ShopCommandResultEvent extends ShopEvent {
    boolean result;
    Long orderId;
}