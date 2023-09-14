package com.intexsoft.webshop.productservicekt.repository

import com.intexsoft.webshop.productservicekt.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("UPDATE Product p SET p.orderQuantity = p.orderQuantity + :increment WHERE p.id = :productId")
    @Modifying(flushAutomatically = true)
    fun updateOrderQuantityByIncrementAndProductId(
        @Param("increment") increment: Short, @Param("productId") productId: Long
    )

    @Query("UPDATE Product p SET p.orderQuantity = p.orderQuantity - :decrement WHERE p.id = :productId")
    @Modifying(flushAutomatically = true)
    fun updateOrderQuantityByDecrementAndProductId(
        @Param("decrement") decrement: Short, @Param("productId") productId: Long
    )
}
