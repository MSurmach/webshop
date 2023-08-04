package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubcategoryService {
    SubcategoryDto createSubcategory(SubcategoryCreateDto subcategoryCreateDto);

    SubcategoryDto findSubcategoryById(Long subcategoryId);

    List<SubcategoryDto> findSubcategories(Pageable pageable);

    SubcategoryDto updateSubcategory(Long subcategoryId, SubcategoryUpdateDto subcategoryUpdateDto);

    void deleteSubcategoryById(Long subcategoryId);
}