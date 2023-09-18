package com.intexsoft.webshop.productservicekt.repository.spec

import com.intexsoft.webshop.productservicekt.model.Product
import com.intexsoft.webshop.productservicekt.model.Product_
import com.intexsoft.webshop.productservicekt.model.Subcategory
import com.intexsoft.webshop.productservicekt.model.Subcategory_
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Fetch
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

object ProductSpecifications {
    fun findAllProducts(): Specification<Product> {
        return Specification<Product> { root: Root<Product>,
                                        _: CriteriaQuery<*>,
                                        criteriaBuilder: CriteriaBuilder ->
            val subcategoryFetch: Fetch<Product, Subcategory> = root.fetch(Product_.subcategory)
            subcategoryFetch.fetch(Subcategory_.category)
            subcategoryFetch.fetch(Subcategory_.attributes)
            root.fetch(Product_.vendor)
            root.fetch(Product_.images)
            root.fetch(Product_.attributeValues)
            criteriaBuilder.conjunction()
        }
    }
}
