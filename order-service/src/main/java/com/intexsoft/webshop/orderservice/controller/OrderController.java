package com.intexsoft.webshop.orderservice.controller;

import com.intexsoft.webshop.orderservice.dto.order.OrderCreateDto;
import com.intexsoft.webshop.orderservice.dto.order.OrderDto;
import com.intexsoft.webshop.orderservice.service.OrderService;
import com.intexsoft.webshop.orderservice.util.JsonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        log.info("IN: request to create a new order received. Request body = {}",
                JsonUtils.getAsString(orderCreateDto));
        OrderDto createdOrderDto = orderService.saveOrder(orderCreateDto);
        log.info("OUT: new order created successfully. Response body = {}", JsonUtils.getAsString(createdOrderDto));
        return ResponseEntity.ok(createdOrderDto);
    }
}