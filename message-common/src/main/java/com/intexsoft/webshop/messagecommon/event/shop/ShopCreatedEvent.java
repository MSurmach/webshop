package com.intexsoft.webshop.messagecommon.event.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShopCreatedEvent extends ShopEvent {
    @JsonProperty("name")
    String name;
}
