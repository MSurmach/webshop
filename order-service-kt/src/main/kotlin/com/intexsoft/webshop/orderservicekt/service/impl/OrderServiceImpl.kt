package com.intexsoft.webshop.orderservicekt.service.impl

import com.intexsoft.webshop.orderservicekt.dto.detail.DetailCreateDto
import com.intexsoft.webshop.orderservicekt.dto.order.OrderCreateDto
import com.intexsoft.webshop.orderservicekt.dto.order.OrderDto
import com.intexsoft.webshop.orderservicekt.log
import com.intexsoft.webshop.orderservicekt.mapper.OrderMapper
import com.intexsoft.webshop.orderservicekt.model.Order
import com.intexsoft.webshop.orderservicekt.model.Status
import com.intexsoft.webshop.orderservicekt.producer.OrderEventProducer
import com.intexsoft.webshop.orderservicekt.repository.OrderRepository
import com.intexsoft.webshop.orderservicekt.service.OrderService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderEventProducer: OrderEventProducer,
    private val orderMapper: OrderMapper
) : OrderService {

    @Transactional
    override fun saveOrder(orderCreateDto: OrderCreateDto): OrderDto {
        log.info("IN: trying to save a new order = $orderCreateDto")
        val initStatus = Status()
        val totalPrice = calculateOrderTotalPrice(orderCreateDto.detailCreateDtos)
        val savedOrder: Order = orderRepository.save(orderMapper.toOrder(orderCreateDto, initStatus, totalPrice))
        log.info("OUT: the order saved successfully. The saved order = $savedOrder")
        orderEventProducer.produceOrderInitializedEvent(orderMapper.toOrderInitializedEvent(savedOrder))
        return orderMapper.toOrderDto(savedOrder)
    }

    private fun calculateOrderTotalPrice(detailCreateDtos: List<DetailCreateDto>): BigDecimal {
        return detailCreateDtos
            .map { it.productPrice.multiply(BigDecimal(it.quantity.toInt())) }
            .fold(BigDecimal.ZERO) { acc: BigDecimal, inc: BigDecimal? -> acc.add(inc) }
            .setScale(2, RoundingMode.HALF_UP)
    }
}
