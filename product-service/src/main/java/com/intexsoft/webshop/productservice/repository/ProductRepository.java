package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaSpecificationExecutor<Product>, JpaRepository<Product, Long> {
}
