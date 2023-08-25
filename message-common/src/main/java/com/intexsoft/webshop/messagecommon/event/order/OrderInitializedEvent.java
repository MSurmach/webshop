package com.intexsoft.webshop.messagecommon.event.order;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInitializedEvent extends OrderEvent {
    Long pickupPointId;
    Long shopId;
    Long userId;
    List<InitOrderProductDetail> initOrderProductDetails;

    @Getter
    @Setter
    public static class InitOrderProductDetail {
        Long productId;
        BigDecimal productPrice;
        Short quantity;
    }
}