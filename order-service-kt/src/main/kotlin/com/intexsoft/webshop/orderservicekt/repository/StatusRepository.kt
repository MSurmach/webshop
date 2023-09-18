package com.intexsoft.webshop.orderservicekt.repository

import com.intexsoft.webshop.orderservicekt.model.Status
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository : JpaRepository<Status, Long>
