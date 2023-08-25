package com.intexsoft.webshop.messagecommon.command.orderorchestrator;

import com.intexsoft.webshop.messagecommon.command.OrderCommand;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckOrderProductCommand extends OrderCommand {
    Long shopId;
    List<OrderProductDetail> orderProductDetails;

    @Getter
    @Setter
    public static class OrderProductDetail {
        Long productId;
        BigDecimal productPrice;
        Short quantity;
    }
}