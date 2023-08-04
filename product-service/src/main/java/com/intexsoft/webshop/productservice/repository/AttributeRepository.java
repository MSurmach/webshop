package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}