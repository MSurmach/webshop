package com.intexsoft.webshop.productservicekt.repository

import com.intexsoft.webshop.productservicekt.model.Vendor
import org.springframework.data.jpa.repository.JpaRepository

interface VendorRepository : JpaRepository<Vendor, Long> {
    fun findByNameIgnoreCase(name: String): Vendor?
}
