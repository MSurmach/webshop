package com.intexsoft.webshop.orderservicekt.service

import com.intexsoft.webshop.orderservicekt.dto.status.StatusDto
import com.intexsoft.webshop.orderservicekt.model.enums.StatusName

interface StatusService {
    fun addStatusToOrderWithId(orderId: Long, statusName: StatusName): StatusDto
}
