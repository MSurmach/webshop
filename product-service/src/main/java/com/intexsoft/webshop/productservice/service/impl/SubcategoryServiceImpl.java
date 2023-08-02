package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.category.CategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import com.intexsoft.webshop.productservice.exception.ResourceNotFoundException;
import com.intexsoft.webshop.productservice.exception.SuchResourceExistsException;
import com.intexsoft.webshop.productservice.mapper.SubcategoryMapper;
import com.intexsoft.webshop.productservice.model.Subcategory;
import com.intexsoft.webshop.productservice.repository.SubcategoryRepository;
import com.intexsoft.webshop.productservice.service.CategoryService;
import com.intexsoft.webshop.productservice.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryService categoryService;
    private final SubcategoryMapper subcategoryMapper;

    @Override
    public SubcategoryDto createSubcategory(SubcategoryCreateDto subcategoryCreateDto, Long categoryId) {
        String createSubcategoryDtoName = subcategoryCreateDto.getName();
        subcategoryRepository.findByNameIgnoreCase(createSubcategoryDtoName)
                .ifPresent(existedSubcategory -> {
                    throw new SuchResourceExistsException("Subcategory with the same name = "
                            + createSubcategoryDtoName + " already exists");
                });
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        Subcategory savedSubcategory = subcategoryRepository.save(
                subcategoryMapper.toSubcategory(subcategoryCreateDto, categoryDto));
        return subcategoryMapper.toSubcategoryDto(savedSubcategory);
    }

    @Override
    public SubcategoryDto findSubcategoryById(Long subcategoryId) {
        Subcategory foundSubcategory = findById(subcategoryId);
        return subcategoryMapper.toSubcategoryDto(foundSubcategory);
    }

    @Override
    public List<SubcategoryDto> findAllSubcategoriesBelongToCategory(Long categoryId) {
        List<Subcategory> subcategoriesByCategoryId = subcategoryRepository.findSubcategoriesByCategoryId(categoryId);
        return subcategoryMapper.toSubcategoryDtos(subcategoriesByCategoryId);
    }

    @Override
    public SubcategoryDto updateSubcategory(Long subcategoryId, SubcategoryUpdateDto subcategoryUpdateDto) {
        findSubcategoryById(subcategoryId);
        Subcategory updatedSubcategory = subcategoryRepository.save(subcategoryMapper.toSubcategory(subcategoryUpdateDto));
        return subcategoryMapper.toSubcategoryDto(updatedSubcategory);
    }

    @Override
    public void deleteSubcategoryById(Long subcategoryId) {
        subcategoryRepository.deleteById(subcategoryId);
    }

    private Subcategory findById(Long subcategoryId) {
        Subcategory foundSubcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(
                () -> new ResourceNotFoundException("The subcategory with id = " + subcategoryId + " not found"));
        return foundSubcategory;
    }
}