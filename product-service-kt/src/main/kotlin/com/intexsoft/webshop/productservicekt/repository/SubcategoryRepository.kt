package com.intexsoft.webshop.productservicekt.repository

import com.intexsoft.webshop.productservicekt.model.Subcategory
import org.springframework.data.jpa.repository.JpaRepository

interface SubcategoryRepository : JpaRepository<Subcategory, Long> {
    fun findByNameIgnoreCase(name: String): Subcategory?
}
