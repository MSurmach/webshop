package com.intexsoft.webshop.productservice.repository.spec;

import com.intexsoft.webshop.productservice.model.Product;
import com.intexsoft.webshop.productservice.model.Product_;
import com.intexsoft.webshop.productservice.model.Subcategory;
import com.intexsoft.webshop.productservice.model.Subcategory_;
import jakarta.persistence.criteria.Fetch;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    private ProductSpecifications() {
    }

    public static Specification<Product> findAllProducts() {
        return (root, query, criteriaBuilder) -> {
            Fetch<Product, Subcategory> subcategoryFetch = root.fetch(Product_.subcategory);
            subcategoryFetch.fetch(Subcategory_.category);
            subcategoryFetch.fetch(Subcategory_.attributes);
            root.fetch(Product_.vendor);
            root.fetch(Product_.images);
            root.fetch(Product_.attributeValues);
            return criteriaBuilder.conjunction();
        };
    }
}
