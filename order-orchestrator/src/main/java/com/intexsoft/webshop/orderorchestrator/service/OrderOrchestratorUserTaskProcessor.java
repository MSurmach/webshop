package com.intexsoft.webshop.orderorchestrator.service;

import com.intexsoft.webshop.messagecommon.event.shop.impl.PickupPointCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.shop.impl.ShopCheckedEvent;
import com.intexsoft.webshop.messagecommon.event.user.impl.UserCheckedEvent;

public interface OrderOrchestratorUserTaskProcessor {

    void processAndCompleteCheckShopResult(ShopCheckedEvent shopCheckedEvent);

    void processAndCompleteCheckPickupPointResult(PickupPointCheckedEvent pickupPointCheckedEvent);


    void processAndCompleteCheckUserResult(UserCheckedEvent userCheckedEvent);
}
