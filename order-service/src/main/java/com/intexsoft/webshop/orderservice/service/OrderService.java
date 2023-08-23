package com.intexsoft.webshop.orderservice.service;

import com.intexsoft.webshop.orderservice.dto.order.OrderCreateDto;
import com.intexsoft.webshop.orderservice.dto.order.OrderDto;

public interface OrderService {
    OrderDto saveOrder(OrderCreateDto orderCreateDto);
}