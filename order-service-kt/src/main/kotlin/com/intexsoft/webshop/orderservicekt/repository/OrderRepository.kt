package com.intexsoft.webshop.orderservicekt.repository

import com.intexsoft.webshop.orderservicekt.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
