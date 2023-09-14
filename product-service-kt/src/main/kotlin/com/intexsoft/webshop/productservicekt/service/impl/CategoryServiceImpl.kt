package com.intexsoft.webshop.productservicekt.service.impl

import com.intexsoft.webshop.productservicekt.dto.category.CategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryDto
import com.intexsoft.webshop.productservicekt.dto.category.CategoryUpdateDto
import com.intexsoft.webshop.productservicekt.exception.conflict.CategoryExistsException
import com.intexsoft.webshop.productservicekt.exception.notfound.CategoryNotFoundException
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.mapper.CategoryMapper
import com.intexsoft.webshop.productservicekt.model.Category
import com.intexsoft.webshop.productservicekt.repository.CategoryRepository
import com.intexsoft.webshop.productservicekt.service.CategoryService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper
) : CategoryService {

    override fun createCategory(categoryCreateDto: CategoryCreateDto): CategoryDto {
        val categoryName: String = categoryCreateDto.name
        log.info("IN: trying to find a category with name = $categoryName")
        if (categoryRepository.findByNameIgnoreCase(categoryName) != null) throw CategoryExistsException(categoryName)
        log.info(
            "category with the name = $categoryName not found, trying to save a new category. " +
                    "Category details = $categoryCreateDto"
        )
        val savedCategory: Category = categoryRepository.save(categoryMapper.toCategory(categoryCreateDto))
        log.info("OUT: the category saved successfully. The saved category details = $savedCategory")
        return categoryMapper.toCategoryDto(savedCategory)
    }

    override fun findCategoryById(categoryId: Long): CategoryDto {
        log.info("IN: trying to find a category by id = $categoryId")
        val foundCategory: Category =
            categoryRepository.findByIdOrNull(categoryId) ?: throw CategoryNotFoundException(categoryId)
        log.info(
            "OUT: the category with id = $categoryId found successfully. Found category details = $foundCategory"
        )
        return categoryMapper.toCategoryDto(foundCategory)
    }

    override fun findCategories(pageable: Pageable): List<CategoryDto> {
        log.info(
            "IN: trying to find categories. Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}"
        )
        val categories: List<Category> = categoryRepository.findAll(pageable).content
        log.info("OUT: ${categories.size} categories found")
        return categoryMapper.toCategoryDtos(categories)
    }

    override fun updateCategory(categoryId: Long, categoryUpdateDto: CategoryUpdateDto): CategoryDto {
        log.info(
            "IN: trying to update a category with id = $categoryId by new details = $categoryUpdateDto"
        )
        val existedCategory: Category =
            categoryRepository.findByIdOrNull(categoryId) ?: throw CategoryNotFoundException(categoryId)
        val updatedCategory: Category = categoryRepository.save(
            categoryMapper.updateCategory(existedCategory, categoryUpdateDto)
        )
        log.info("OUT: the category updated successfully. The updated category details = $updatedCategory")
        return categoryMapper.toCategoryDto(updatedCategory)
    }

    override fun deleteCategoryById(categoryId: Long) {
        log.info("IN: trying to delete a category by id = $categoryId")
        categoryRepository.deleteById(categoryId)
        log.info("OUT: the category with id = $categoryId deleted successfully")
    }
}