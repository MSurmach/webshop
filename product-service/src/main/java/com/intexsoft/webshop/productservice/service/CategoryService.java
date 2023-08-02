package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryCreateDto categoryCreateDto);

    CategoryDto findCategoryById(Long categoryId);

    List<CategoryDto> findAllCategories();

    CategoryDto updateCategory(Long categoryId, CategoryUpdateDto categoryUpdateDto);

    void deleteCategoryById(Long categoryId);
}