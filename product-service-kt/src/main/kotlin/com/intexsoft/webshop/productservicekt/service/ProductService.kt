package com.intexsoft.webshop.productservicekt.service

import com.intexsoft.webshop.productservicekt.dto.product.ProductCreateDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductUpdateDto
import org.springframework.data.domain.Pageable

interface ProductService {
    fun createProduct(productCreateDto: ProductCreateDto): ProductDto
    fun findProductById(productId: Long): ProductDto
    fun findProducts(pageable: Pageable): List<ProductDto>
    fun updateProduct(productId: Long, productUpdateDto: ProductUpdateDto): ProductDto
    fun deleteProductById(productId: Long)
}