package com.intexsoft.webshop.messagecommon.event.shop.impl;

import com.intexsoft.webshop.messagecommon.event.shop.ShopEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopProductLinkCreatedEvent extends ShopEvent {
    Long productId;
    BigDecimal price;
    Short quantity;
}