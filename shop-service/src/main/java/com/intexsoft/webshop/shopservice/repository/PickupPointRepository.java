package com.intexsoft.webshop.shopservice.repository;

import com.intexsoft.webshop.shopservice.model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
    @Query("SELECT count(p)>0 FROM PickupPoint p WHERE p.shop.id = ?1 AND p.id = ?2")
    boolean existsShopByIdAndByPickupPointId(Long shopId, Long pickupPointId);
}
