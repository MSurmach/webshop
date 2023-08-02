package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByNameIgnoreCase(String name);

    List<Subcategory> findSubcategoriesByCategoryId(Long categoryId);
}
