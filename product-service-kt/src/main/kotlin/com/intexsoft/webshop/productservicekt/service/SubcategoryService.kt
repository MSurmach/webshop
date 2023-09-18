package com.intexsoft.webshop.productservicekt.service

import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryUpdateDto
import org.springframework.data.domain.Pageable

interface SubcategoryService {
    fun createSubcategory(subcategoryCreateDto: SubcategoryCreateDto): SubcategoryDto
    fun findSubcategoryById(subcategoryId: Long): SubcategoryDto
    fun findSubcategories(pageable: Pageable): List<SubcategoryDto>
    fun updateSubcategory(subcategoryId: Long, subcategoryUpdateDto: SubcategoryUpdateDto): SubcategoryDto
    fun deleteSubcategoryById(subcategoryId: Long)
}