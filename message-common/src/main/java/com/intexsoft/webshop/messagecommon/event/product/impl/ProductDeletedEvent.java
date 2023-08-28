package com.intexsoft.webshop.messagecommon.event.product.impl;

import com.intexsoft.webshop.messagecommon.event.product.ProductEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDeletedEvent extends ProductEvent {

}