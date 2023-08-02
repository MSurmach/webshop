package com.intexsoft.webshop.productservice.controller;

import com.intexsoft.webshop.productservice.dto.category.CategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryUpdateDto;
import com.intexsoft.webshop.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;
import static java.lang.Integer.MAX_VALUE;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) {
        log.info("IN: request to create a new category received. Request body = {}", getAsString(categoryCreateDto));
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryCreateDto);
        log.info("OUT: new category created successfully. Response body = {}", getAsString(createdCategoryDto));
        return ResponseEntity.ok(createdCategoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories(@PageableDefault(size = MAX_VALUE) Pageable pageable) {
        log.info("IN: request to find categories received. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<CategoryDto> categoryDtos = categoryService.findCategories(pageable);
        log.info("OUT: {} categories found", categoryDtos.size());
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable @Positive Long categoryId) {
        log.info("IN: request to find a category by id = {} received", categoryId);
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        log.info("OUT: the category with id = {} found successfully. Response body = {}",
                categoryId, getAsString(categoryDto));
        return ResponseEntity.ok(categoryDto);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable @Positive Long categoryId,
                                                      @RequestBody @Valid CategoryUpdateDto categoryUpdateDto) {
        log.info("IN: request to update a category with id = {} received. Request body = {}",
                categoryId, getAsString(categoryUpdateDto));
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryId, categoryUpdateDto);
        log.info("OUT: the category with id = {} updated successfully. Response body = {}",
                categoryId, getAsString(categoryUpdateDto));
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable @Positive Long categoryId) {
        log.info("IN: request to delete a category by id = {} received", categoryId);
        categoryService.deleteCategoryById(categoryId);
        log.info("OUT: the category with id = {} deleted successfully", categoryId);
        return ResponseEntity.noContent().build();
    }
}