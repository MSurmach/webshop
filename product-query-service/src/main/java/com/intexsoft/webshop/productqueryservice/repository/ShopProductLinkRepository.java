package com.intexsoft.webshop.productqueryservice.repository;

import com.intexsoft.webshop.productqueryservice.model.ShopProductLink;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShopProductLinkRepository extends MongoRepository<ShopProductLink, Long> {
    List<ShopProductLink> findAllByShopId(Long productId);
}