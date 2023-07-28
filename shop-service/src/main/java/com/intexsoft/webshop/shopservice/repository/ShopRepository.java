package com.intexsoft.webshop.shopservice.repository;

import com.intexsoft.webshop.shopservice.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findShopByNameIgnoreCaseOrEmailIgnoreCase(String name, String email);
}