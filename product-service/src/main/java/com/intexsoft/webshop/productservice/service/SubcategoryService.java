package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;

import java.util.List;

public interface SubcategoryService {
    SubcategoryDto createSubcategory(SubcategoryCreateDto subcategoryCreateDto, Long categoryId);

    SubcategoryDto findSubcategoryById(Long subcategoryId);

    List<SubcategoryDto> findAllSubcategoriesBelongToCategory(Long categoryId);

    SubcategoryDto updateSubcategory(Long subcategoryId, SubcategoryUpdateDto subcategoryUpdateDto);

    void deleteSubcategoryById(Long subcategoryId);
}