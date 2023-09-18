package com.intexsoft.webshop.productservicekt.controller

import com.intexsoft.webshop.productservicekt.dto.product.ProductCreateDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductUpdateDto
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.service.ProductService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
@Validated
class ProductController(
    private val productService: ProductService
) {
    @PostMapping
    fun createProduct(@RequestBody @Valid productCreateDto: ProductCreateDto): ResponseEntity<ProductDto> {
        log.info("IN: request to create a new product received. Request body = $productCreateDto")
        val createdProductDto: ProductDto = productService.createProduct(productCreateDto)
        log.info("OUT: new product created successfully. Response body = $createdProductDto")
        return ResponseEntity.ok(createdProductDto)
    }

    @GetMapping
    fun findProducts(@PageableDefault(size = Int.MAX_VALUE) pageable: Pageable): ResponseEntity<List<ProductDto>> {
        log.info(
            "IN: request to find products received. Page size = ${pageable.pageSize}," +
                    " page number = ${pageable.pageNumber}"
        )
        val productDtos: List<ProductDto> = productService.findProducts(pageable)
        log.info("OUT: response will return ${productDtos.size} products")
        return ResponseEntity.ok(productDtos)
    }

    @GetMapping("/{productId}")
    fun findProductById(@PathVariable @Positive productId: Long): ResponseEntity<ProductDto> {
        log.info("IN: request to find a product by id = $productId received")
        val productDto: ProductDto = productService.findProductById(productId)
        log.info("OUT: the category with id = $productId found successfully. Response body = $productDto")
        return ResponseEntity.ok(productDto)
    }

    @PutMapping("/{productId}")
    fun updateProduct(
        @PathVariable @Positive productId: Long,
        @RequestBody @Valid productUpdateDto: ProductUpdateDto
    ): ResponseEntity<ProductDto> {
        log.info("IN: request to update a product with id = $productId received. Request body = $productUpdateDto")
        val updatedProductDto: ProductDto = productService.updateProduct(productId, productUpdateDto)
        log.info("OUT: the product with id = $productId updated successfully. Response body = $updatedProductDto")
        return ResponseEntity.ok(updatedProductDto)
    }

    @DeleteMapping("/{productId}")
    fun deleteProductById(@PathVariable @Positive productId: Long): ResponseEntity<Unit> {
        log.info("IN: request to delete a product by id = $productId received")
        productService.deleteProductById(productId)
        log.info("OUT: the product with id = $productId deleted successfully")
        return ResponseEntity.noContent().build()
    }
}