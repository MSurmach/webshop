package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.category.CategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryUpdateDto;
import com.intexsoft.webshop.productservice.exception.ResourceNotFoundException;
import com.intexsoft.webshop.productservice.exception.SuchResourceExistsException;
import com.intexsoft.webshop.productservice.mapper.CategoryMapper;
import com.intexsoft.webshop.productservice.model.Category;
import com.intexsoft.webshop.productservice.repository.CategoryRepository;
import com.intexsoft.webshop.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {
        String categoryName = categoryCreateDto.getName();
        log.info("IN: trying to find a category with name = {}", categoryName);
        categoryRepository.findByNameIgnoreCase(categoryName)
                .ifPresent(existedCategory -> {
                    throw new SuchResourceExistsException("Category with the same name = "
                            + categoryName + " already exists");
                });
        log.info("category with the name = {} not found, trying to save a new category. Category details = {}",
                categoryName, getAsString(categoryCreateDto));
        Category savedCategory = categoryRepository.save(categoryMapper.toCategory(categoryCreateDto));
        log.info("OUT: the category saved successfully. The saved category details = {}",
                getAsString(savedCategory));
        return categoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {
        log.info("IN: trying to find a category by id = {}", categoryId);
        Category foundCategory = findById(categoryId);
        log.info("OUT: the category with id = {} found successfully. Found category details = {}",
                categoryId, getAsString(foundCategory));
        return categoryMapper.toCategoryDto(foundCategory);
    }

    @Override
    public List<CategoryDto> findCategories(Pageable pageable) {
        log.info("IN: trying to find categories. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<Category> categories = categoryRepository.findAll(pageable).getContent();
        log.info("OUT: {} categories found", categories.size());
        return categoryMapper.toCategoryDtos(categories);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryUpdateDto categoryUpdateDto) {
        log.info("IN: trying to update a category with id = {} by new details = {}",
                categoryId, getAsString(categoryUpdateDto));
        Category existedCategory = findById(categoryId);
        Category updatedCategory = categoryRepository.save(
                categoryMapper.updateCategory(existedCategory, categoryUpdateDto));
        log.info("OUT: the category updated successfully. The updated category details = {}",
                getAsString(updatedCategory));
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        log.info("IN: trying to delete a category by id = {}", categoryId);
        categoryRepository.deleteById(categoryId);
        log.info("OUT: the category with id = {} deleted successfully", categoryId);
    }

    private Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("The category with id = " + categoryId + " not found"));
    }
}