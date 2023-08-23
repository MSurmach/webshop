package com.intexsoft.webshop.orderservice.service.impl;

import com.intexsoft.webshop.orderservice.dto.status.StatusDto;
import com.intexsoft.webshop.orderservice.mapper.StatusMapper;
import com.intexsoft.webshop.orderservice.model.Order;
import com.intexsoft.webshop.orderservice.model.Status;
import com.intexsoft.webshop.orderservice.model.enums.StatusName;
import com.intexsoft.webshop.orderservice.repository.OrderRepository;
import com.intexsoft.webshop.orderservice.repository.StatusRepository;
import com.intexsoft.webshop.orderservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    private final OrderRepository orderRepository;
    private final StatusMapper statusMapper;

    @Override
    public StatusDto addStatusToOrderWithId(Long orderId, StatusName statusName) {
        log.info("IN: try to add new status = {} to order with id = {}", statusName, orderId);
        Order foundOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order with id= " + orderId + " not found"));
        Status addedStatus = statusRepository.save(statusMapper.toStatus(foundOrder, statusName));
        log.info("IN: status = {} successfully added to order with id = {}", addedStatus.getStatusName(), orderId);
        return statusMapper.toStatusDto(addedStatus);
    }
}
