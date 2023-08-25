package com.intexsoft.webshop.messagecommon.command.orderorchestrator;

import com.intexsoft.webshop.messagecommon.command.ChangeProductOrderQuantityType;
import com.intexsoft.webshop.messagecommon.command.OrderCommand;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeProductOrderQuantityCommand extends OrderCommand {
    ChangeProductOrderQuantityType changeType;

    Map<Long, Short> productIdDeltaOrderQuantityMap;
}