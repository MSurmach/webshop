package com.intexsoft.webshop.shopservice.repository;

import com.intexsoft.webshop.shopservice.model.ShopProductLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ShopProductLinkRepository extends JpaRepository<ShopProductLink, Long> {
    @Query("SELECT count(spl)>0 FROM ShopProductLink spl " +
            "WHERE spl.shop.id = ?1 AND spl.product.id = ?2 AND spl.quantity >= ?3 AND spl.price = ?4")
    boolean existsByShopIdAndProductIdAndQuantityLessThanAndPriceEquals(Long shopId, Long productId, Short quantity, BigDecimal price);
}