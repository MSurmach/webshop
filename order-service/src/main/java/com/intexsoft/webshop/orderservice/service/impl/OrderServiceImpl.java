package com.intexsoft.webshop.orderservice.service.impl;

import com.intexsoft.webshop.orderservice.dto.detail.DetailCreateDto;
import com.intexsoft.webshop.orderservice.dto.order.OrderCreateDto;
import com.intexsoft.webshop.orderservice.dto.order.OrderDto;
import com.intexsoft.webshop.orderservice.mapper.OrderMapper;
import com.intexsoft.webshop.orderservice.model.Order;
import com.intexsoft.webshop.orderservice.model.Status;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import com.intexsoft.webshop.orderservice.producer.OrderEventProducer;
import com.intexsoft.webshop.orderservice.repository.OrderRepository;
import com.intexsoft.webshop.orderservice.service.OrderService;
import com.intexsoft.webshop.orderservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDto saveOrder(OrderCreateDto orderCreateDto) {
        log.info("IN: trying to save a new order = {}", JsonUtils.getAsString(orderCreateDto));
        Status initStatus = new Status(StatusName.INITIALIZED);
        BigDecimal totalPrice = calculateOrderTotalPrice(orderCreateDto.getDetailCreateDtos());
        Order savedOrder = orderRepository.save(orderMapper.toOrder(orderCreateDto, initStatus, totalPrice));
        log.info("OUT: the order saved successfully. The saved order = {}",
                JsonUtils.getAsString(savedOrder));
        orderEventProducer.produceOrderInitializedEvent(orderMapper.toOrderInitializedEvent(savedOrder));
        return orderMapper.toOrderDto(savedOrder);
    }

    private BigDecimal calculateOrderTotalPrice(List<DetailCreateDto> detailCreateDtos) {
        return detailCreateDtos.stream()
                .map(detailCreateDto -> {
                    BigDecimal productPrice = detailCreateDto.getProductPrice();
                    Short quantity = detailCreateDto.getQuantity();
                    return productPrice.multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
