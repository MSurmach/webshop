package com.intexsoft.webshop.messagecommon.command.orderorchestrator;

import com.intexsoft.webshop.messagecommon.command.OrderCommand;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckOrderPickupPointCommand extends OrderCommand {
    Long shopId;
    Long pickupPointId;
}
