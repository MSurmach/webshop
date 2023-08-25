package com.intexsoft.webshop.messagecommon.event.shop.impl;

import com.intexsoft.webshop.messagecommon.event.shop.ShopCommandResultEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestToShopAddedEvent extends ShopCommandResultEvent {
}