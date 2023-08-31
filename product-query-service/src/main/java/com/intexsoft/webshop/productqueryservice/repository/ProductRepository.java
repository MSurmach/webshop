package com.intexsoft.webshop.productqueryservice.repository;

import com.intexsoft.webshop.productqueryservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface ProductRepository extends MongoRepository<Product, Long> {
    Page<Product> findAllByIdIn(Set<Long> ids, Pageable pageable);
}