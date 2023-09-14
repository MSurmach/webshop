package com.intexsoft.webshop.orderservicekt.service.impl

import com.intexsoft.webshop.orderservicekt.dto.status.StatusDto
import com.intexsoft.webshop.orderservicekt.log
import com.intexsoft.webshop.orderservicekt.mapper.StatusMapper
import com.intexsoft.webshop.orderservicekt.model.Order
import com.intexsoft.webshop.orderservicekt.model.Status
import com.intexsoft.webshop.orderservicekt.model.enums.StatusName
import com.intexsoft.webshop.orderservicekt.repository.OrderRepository
import com.intexsoft.webshop.orderservicekt.repository.StatusRepository
import com.intexsoft.webshop.orderservicekt.service.StatusService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StatusServiceImpl(
    private val statusRepository: StatusRepository,
    private val orderRepository: OrderRepository,
    private val statusMapper: StatusMapper
) : StatusService {

    override fun addStatusToOrderWithId(orderId: Long, statusName: StatusName): StatusDto {
        log.info("IN: try to add new status = $statusName to order with id = $orderId")
        val foundOrder: Order =
            orderRepository.findByIdOrNull(orderId) ?: throw RuntimeException("Order with id= $orderId not found")
        val addedStatus: Status = statusRepository.save(statusMapper.toStatus(foundOrder, statusName))
        log.info("IN: status = ${addedStatus.statusName} successfully added to order with id = $orderId")
        return statusMapper.toStatusDto(addedStatus)
    }
}
