package com.intexsoft.webshop.orderservicekt.controller

import com.intexsoft.webshop.orderservicekt.dto.order.OrderCreateDto
import com.intexsoft.webshop.orderservicekt.dto.order.OrderDto
import com.intexsoft.webshop.orderservicekt.log
import com.intexsoft.webshop.orderservicekt.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
@Validated
class OrderController(
    private val orderService: OrderService
) {
    @PostMapping
    fun createOrder(@RequestBody orderCreateDto: @Valid OrderCreateDto): ResponseEntity<OrderDto> {
        log.info("IN: request to create a new order received. Request body = $orderCreateDto")
        val createdOrderDto: OrderDto = orderService.saveOrder(orderCreateDto)
        log.info("OUT: new order created successfully. Response body = $createdOrderDto")
        return ResponseEntity.ok(createdOrderDto)
    }
}