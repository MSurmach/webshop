package com.intexsoft.webshop.orderservice.service;

import com.intexsoft.webshop.orderservice.dto.status.StatusDto;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;

public interface StatusService {
    StatusDto addStatusToOrderWithId(Long orderId, StatusName statusName);
}
