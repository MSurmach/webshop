package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
