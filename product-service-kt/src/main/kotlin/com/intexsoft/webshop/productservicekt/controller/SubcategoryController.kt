package com.intexsoft.webshop.productservicekt.controller

import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryCreateDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryDto
import com.intexsoft.webshop.productservicekt.dto.subcategory.SubcategoryUpdateDto
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.service.SubcategoryService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subcategory")
@Validated
class SubcategoryController(
    private val subcategoryService: SubcategoryService
) {
    @PostMapping
    fun createSubcategory(@RequestBody @Valid subcategoryCreateDto: SubcategoryCreateDto): ResponseEntity<SubcategoryDto> {
        log.info("IN: request to create a new subcategory received. Request body = $subcategoryCreateDto")
        val createdSubCategoryDto: SubcategoryDto = subcategoryService.createSubcategory(subcategoryCreateDto)
        log.info("OUT: new subcategory created successfully. Response body = $createdSubCategoryDto")
        return ResponseEntity.ok(createdSubCategoryDto)
    }

    @GetMapping
    fun findAllSubcategories(@PageableDefault(size = Int.MAX_VALUE) pageable: Pageable): ResponseEntity<List<SubcategoryDto>> {
        log.info(
            "IN: request to find subcategories received." +
                    " Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}"
        )
        val subcategoryDtos: List<SubcategoryDto> = subcategoryService.findSubcategories(pageable)
        log.info("OUT: ${subcategoryDtos.size} subcategories found")
        return ResponseEntity.ok(subcategoryDtos)
    }

    @GetMapping("/{subcategoryId}")
    fun findSubcategoryById(@PathVariable @Positive subcategoryId: Long): ResponseEntity<SubcategoryDto> {
        log.info("IN: request to find a subcategory by id = $subcategoryId received")
        val subcategoryDto: SubcategoryDto = subcategoryService.findSubcategoryById(subcategoryId)
        log.info("OUT: the subcategory with id = $subcategoryId found successfully. Response body = $subcategoryDto")
        return ResponseEntity.ok(subcategoryDto)
    }

    @PutMapping("/{subcategoryId}")
    fun updateSubcategory(
        @PathVariable @Positive subcategoryId: Long,
        @RequestBody @Valid subcategoryUpdateDto: SubcategoryUpdateDto
    ): ResponseEntity<SubcategoryDto> {
        log.info(
            "IN: request to update a subcategory with id = $subcategoryId received." +
                    " Request body = $subcategoryUpdateDto"
        )
        val updatedSubcategoryDto: SubcategoryDto =
            subcategoryService.updateSubcategory(subcategoryId, subcategoryUpdateDto)
        log.info(
            "OUT: the subcategory with id = $subcategoryId updated successfully." +
                    " Response body = $updatedSubcategoryDto"
        )
        return ResponseEntity.ok(updatedSubcategoryDto)
    }

    @DeleteMapping("/{subcategoryId}")
    fun deleteSubcategoryById(@PathVariable @Positive subcategoryId: Long): ResponseEntity<Unit> {
        log.info("IN: request to delete a subcategory by id = $subcategoryId received")
        subcategoryService.deleteSubcategoryById(subcategoryId)
        log.info("OUT: the subcategory with id = $subcategoryId deleted successfully")
        return ResponseEntity.noContent().build()
    }
}