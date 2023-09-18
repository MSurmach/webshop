package com.intexsoft.webshop.productservicekt.service

import com.intexsoft.webshop.productservicekt.dto.category.CategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryUpdateDto
import org.springframework.data.domain.Pageable

interface CategoryService {
    fun createCategory(categoryCreateDto: CategoryCreateDto): CategoryDto
    fun findCategoryById(categoryId: Long): CategoryDto
    fun findCategories(pageable: Pageable): List<CategoryDto>
    fun updateCategory(categoryId: Long, categoryUpdateDto: CategoryUpdateDto): CategoryDto
    fun deleteCategoryById(categoryId: Long)
}