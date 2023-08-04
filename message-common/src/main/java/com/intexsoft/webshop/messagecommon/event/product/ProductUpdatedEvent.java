package com.intexsoft.webshop.messagecommon.event.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdatedEvent extends ProductEvent {
    String name;
}
