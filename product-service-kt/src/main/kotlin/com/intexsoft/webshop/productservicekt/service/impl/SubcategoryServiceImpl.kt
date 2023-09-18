package com.intexsoft.webshop.productservicekt.service.impl

import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryUpdateDto
import com.intexsoft.webshop.productservicekt.exception.conflict.SubcategoryExistsException
import com.intexsoft.webshop.productservicekt.exception.notfound.CategoryNotFoundException
import com.intexsoft.webshop.productservicekt.exception.notfound.SubcategoryNotFoundException
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.mapper.SubcategoryMapper
import com.intexsoft.webshop.productservicekt.model.Category
import com.intexsoft.webshop.productservicekt.model.Subcategory
import com.intexsoft.webshop.productservicekt.repository.CategoryRepository
import com.intexsoft.webshop.productservicekt.repository.SubcategoryRepository
import com.intexsoft.webshop.productservicekt.service.SubcategoryService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SubcategoryServiceImpl(
    private val subcategoryRepository: SubcategoryRepository,
    private val categoryRepository: CategoryRepository,
    private val subcategoryMapper: SubcategoryMapper,
) : SubcategoryService {

    override fun createSubcategory(subcategoryCreateDto: SubcategoryCreateDto): SubcategoryDto {
        val subcategoryName: String = subcategoryCreateDto.name!!
        log.info("IN: trying to find a subcategory with name = $subcategoryName")
        if (subcategoryRepository.findByNameIgnoreCase(subcategoryName) != null)
            throw SubcategoryExistsException(subcategoryName)
        log.info(
            "subcategory with the name = $subcategoryName not found, trying to save a new subcategory." +
                    " Subcategory details = $subcategoryCreateDto"
        )
        val categoryId: Long = subcategoryCreateDto.categoryId
        val category: Category =
            categoryRepository.findByIdOrNull(categoryId) ?: throw CategoryNotFoundException(categoryId)
        val newSubcategory = subcategoryMapper.toSubcategory(subcategoryCreateDto, category)
        subcategoryMapper.linkAttributesToSubcategory(newSubcategory, subcategoryCreateDto)
        val savedSubcategory: Subcategory = subcategoryRepository.save(newSubcategory)
        log.info("OUT: the subcategory saved successfully. The saved subcategory details = $savedSubcategory")
        return subcategoryMapper.toSubcategoryDto(savedSubcategory)
    }

    override fun findSubcategoryById(subcategoryId: Long): SubcategoryDto {
        log.info("IN: trying to find a subcategory by id = $subcategoryId")
        val foundSubcategory: Subcategory =
            subcategoryRepository.findByIdOrNull(subcategoryId) ?: throw SubcategoryNotFoundException(subcategoryId)
        log.info(
            "OUT: the subcategory with id = $subcategoryId found successfully." +
                    " Found subcategory details = $foundSubcategory"
        )
        return subcategoryMapper.toSubcategoryDto(foundSubcategory)
    }

    override fun findSubcategories(pageable: Pageable): List<SubcategoryDto> {
        log.info(
            "IN: trying to find subcategories. Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}"
        )
        val subcategories: List<Subcategory> = subcategoryRepository.findAll(pageable).content
        log.info("OUT: ${subcategories.size} subcategories found")
        return subcategoryMapper.toSubcategoryDtos(subcategories)
    }

    override fun updateSubcategory(subcategoryId: Long, subcategoryUpdateDto: SubcategoryUpdateDto): SubcategoryDto {
        log.info("IN: trying to update a subcategory with id = $subcategoryId by new details = $subcategoryUpdateDto")
        val existedSubcategory: Subcategory =
            subcategoryRepository.findByIdOrNull(subcategoryId) ?: throw SubcategoryNotFoundException(subcategoryId)
        val updatedSubcategory: Subcategory = subcategoryRepository.save(
            subcategoryMapper.updateSubcategory(existedSubcategory, subcategoryUpdateDto)
        )
        log.info("OUT: the subcategory updated successfully. The updated subcategory details = $updatedSubcategory")
        return subcategoryMapper.toSubcategoryDto(updatedSubcategory)
    }

    override fun deleteSubcategoryById(subcategoryId: Long) {
        log.info("IN: trying to delete a subcategory by id = $subcategoryId")
        subcategoryRepository.deleteById(subcategoryId)
        log.info("OUT: the subcategory with id = $subcategoryId deleted successfully")
    }
}