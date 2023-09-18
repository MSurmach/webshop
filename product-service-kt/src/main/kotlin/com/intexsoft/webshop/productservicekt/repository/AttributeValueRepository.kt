package com.intexsoft.webshop.productservicekt.repository

import com.intexsoft.webshop.productservicekt.model.AttributeValue
import org.springframework.data.jpa.repository.JpaRepository

interface AttributeValueRepository : JpaRepository<AttributeValue, Long>
