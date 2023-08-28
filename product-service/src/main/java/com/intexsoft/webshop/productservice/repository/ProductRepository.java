package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaSpecificationExecutor<Product>, JpaRepository<Product, Long> {
    @Query("UPDATE Product p SET p.orderQuantity = p.orderQuantity + ?1 WHERE p.id = ?2")
    @Modifying(flushAutomatically = true)
    void updateOrderQuantityByIncrementAndProductId(Short increment, Long productId);

    @Query("UPDATE Product p SET p.orderQuantity = p.orderQuantity - ?1 WHERE p.id = ?2")
    @Modifying(flushAutomatically = true)
    void updateOrderQuantityByDecrementAndProductId(Short decrement, Long productId);
}