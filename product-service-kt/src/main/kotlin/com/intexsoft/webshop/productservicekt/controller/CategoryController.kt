package com.intexsoft.webshop.productservicekt.controller

import com.intexsoft.webshop.productservicekt.dto.category.CategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryUpdateDto
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.service.CategoryService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
@Validated
class CategoryController(
    private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(@RequestBody @Valid categoryCreateDto: CategoryCreateDto): ResponseEntity<CategoryDto> {
        log.info("IN: request to create a new category received. Request body = $categoryCreateDto")
        val createdCategoryDto: CategoryDto = categoryService.createCategory(categoryCreateDto)
        log.info("OUT: new category created successfully. Response body = $createdCategoryDto")
        return ResponseEntity.ok(createdCategoryDto)
    }

    @GetMapping
    fun findAllCategories(@PageableDefault(size = Int.MAX_VALUE) pageable: Pageable): ResponseEntity<List<CategoryDto>> {
        log.info(
            "IN: request to find categories received." +
                    " Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}"
        )
        val categoryDtos: List<CategoryDto> = categoryService.findCategories(pageable)
        log.info("OUT: ${categoryDtos.size} categories found")
        return ResponseEntity.ok(categoryDtos)
    }

    @GetMapping("/{categoryId}")
    fun findCategoryById(@PathVariable @Positive categoryId: Long): ResponseEntity<CategoryDto> {
        log.info("IN: request to find a category by id = $categoryId received")
        val categoryDto: CategoryDto = categoryService.findCategoryById(categoryId)
        log.info("OUT: the category with id = $categoryId found successfully. Response body = $categoryDto")
        return ResponseEntity.ok(categoryDto)
    }

    @PutMapping("/{categoryId}")
    fun updateCategory(
        @PathVariable @Positive categoryId: Long,
        @RequestBody @Valid categoryUpdateDto: CategoryUpdateDto
    ): ResponseEntity<CategoryDto> {
        log.info("IN: request to update a category with id = $categoryId received. Request body = $categoryUpdateDto")
        val updatedCategoryDto: CategoryDto = categoryService.updateCategory(categoryId, categoryUpdateDto)
        log.info("OUT: the category with id = $categoryId updated successfully. Response body = $categoryUpdateDto")
        return ResponseEntity.ok(updatedCategoryDto)
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategoryById(@PathVariable @Positive categoryId: Long): ResponseEntity<Unit> {
        log.info("IN: request to delete a category by id = $categoryId received")
        categoryService.deleteCategoryById(categoryId)
        log.info("OUT: the category with id = $categoryId deleted successfully")
        return ResponseEntity.noContent().build()
    }
}