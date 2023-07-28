package com.intexsoft.webshop.messagecommon.event.shop;

import com.intexsoft.webshop.messagecommon.event.BaseEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ShopEvent extends BaseEvent {
    Long shopId;
}
