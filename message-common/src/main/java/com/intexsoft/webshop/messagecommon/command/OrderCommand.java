package com.intexsoft.webshop.messagecommon.command;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class OrderCommand extends BaseCommand {
    Long orderId;
}
