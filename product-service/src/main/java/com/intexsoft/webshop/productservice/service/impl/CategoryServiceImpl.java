package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.category.CategoryUpdateDto;
import com.intexsoft.webshop.productservice.exception.ResourceNotFoundException;
import com.intexsoft.webshop.productservice.exception.SuchResourceExistsException;
import com.intexsoft.webshop.productservice.mapper.CategoryMapper;
import com.intexsoft.webshop.productservice.model.Category;
import com.intexsoft.webshop.productservice.repository.CategoryRepository;
import com.intexsoft.webshop.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {
        String createCategoryDtoName = categoryCreateDto.getName();
        findCategoryByName(createCategoryDtoName).ifPresent(existedCategory -> {
            throw new SuchResourceExistsException("Category with the same name = "
                    + createCategoryDtoName + " already exists");
        });
        Category savedCategory = categoryRepository.save(categoryMapper.toCategory(categoryCreateDto));
        return categoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {
        Category foundCategory = findById(categoryId);
        return categoryMapper.toCategoryDto(foundCategory);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryDtos(categories);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryUpdateDto categoryUpdateDto) {
        Category foundCategory = findById(categoryId);
        Category updatedCategory = categoryRepository.save(
                categoryMapper.updateCategory(foundCategory, categoryUpdateDto));
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private Optional<Category> findCategoryByName(String name) {
        Optional<Category> foundCategory = categoryRepository.findByNameIgnoreCase(name);
        return foundCategory;
    }

    private Category findById(Long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("The category with id = " + categoryId + " not found"));
        return foundCategory;
    }
}