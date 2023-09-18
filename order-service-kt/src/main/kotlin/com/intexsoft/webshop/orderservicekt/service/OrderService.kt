package com.intexsoft.webshop.orderservicekt.service

import com.intexsoft.webshop.orderservicekt.dto.order.OrderCreateDto
import com.intexsoft.webshop.orderservicekt.dto.order.OrderDto

interface OrderService {
    fun saveOrder(orderCreateDto: OrderCreateDto): OrderDto
}