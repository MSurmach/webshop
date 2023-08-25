package com.intexsoft.webshop.orderorchestrator.service;

import com.intexsoft.webshop.messagecommon.event.product.impl.ProductOrderQuantityIncrementedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.OrderRequestToShopAddedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ProductCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;

public interface OrderOrchestratorUserTaskProcessor {

    void processAndCompleteCheckShopResult(ShopCheckedEvent shopCheckedEvent);

    void processAndCompleteCheckPickupPointResult(PickupPointCheckedEvent pickupPointCheckedEvent);

    void processAndCompleteCheckUserResult(UserCheckedEvent userCheckedEvent);

    void processAndCompleteProductOrderQuantityIncrementedResult(ProductOrderQuantityIncrementedEvent productOrderQuantityIncrementedEvent);

    void processAndCompleteCheckProductResult(ProductCheckedEvent productCheckedEvent);

    void processAndCompleteOrderRequestResult(OrderRequestToShopAddedEvent orderRequestToShopAddedEvent);
}