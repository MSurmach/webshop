package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    List<Attribute> findAllByIdInAndSubcategoryId(List<Long> attributeIds, Long subcategoryId);
}
