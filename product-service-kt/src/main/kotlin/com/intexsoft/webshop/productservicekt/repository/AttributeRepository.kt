package com.intexsoft.webshop.productservicekt.repository

import com.intexsoft.webshop.productservicekt.model.Attribute
import org.springframework.data.jpa.repository.JpaRepository

interface AttributeRepository : JpaRepository<Attribute, Long>