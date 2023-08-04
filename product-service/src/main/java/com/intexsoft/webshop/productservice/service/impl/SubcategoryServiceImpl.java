package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import com.intexsoft.webshop.productservice.exception.conflict409.SubcategoryExistsException;
import com.intexsoft.webshop.productservice.exception.notfound404.CategoryNotFoundException;
import com.intexsoft.webshop.productservice.exception.notfound404.SubcategoryNotFoundException;
import com.intexsoft.webshop.productservice.mapper.SubcategoryMapper;
import com.intexsoft.webshop.productservice.model.Category;
import com.intexsoft.webshop.productservice.model.Subcategory;
import com.intexsoft.webshop.productservice.repository.CategoryRepository;
import com.intexsoft.webshop.productservice.repository.SubcategoryRepository;
import com.intexsoft.webshop.productservice.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryMapper subcategoryMapper;

    @Override
    public SubcategoryDto createSubcategory(SubcategoryCreateDto subcategoryCreateDto) {
        String subcategoryName = subcategoryCreateDto.getName();
        log.info("IN: trying to find a subcategory with name = {}", subcategoryName);
        subcategoryRepository.findByNameIgnoreCase(subcategoryName)
                .ifPresent(existedSubcategory -> {
                    throw new SubcategoryExistsException(existedSubcategory.getName());
                });
        log.info("subcategory with the name = {} not found, trying to save a new subcategory. Subcategory details = {}",
                subcategoryName, getAsString(subcategoryCreateDto));
        Long categoryId = subcategoryCreateDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Subcategory savedSubcategory = subcategoryRepository.save(
                subcategoryMapper.toSubcategory(subcategoryCreateDto, category));
        log.info("OUT: the subcategory saved successfully. The saved subcategory details = {}",
                getAsString(savedSubcategory));
        return subcategoryMapper.toSubcategoryDto(savedSubcategory);
    }

    @Override
    public SubcategoryDto findSubcategoryById(Long subcategoryId) {
        log.info("IN: trying to find a subcategory by id = {}", subcategoryId);
        Subcategory foundSubcategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
        log.info("OUT: the subcategory with id = {} found successfully. Found subcategory details = {}",
                subcategoryId, getAsString(foundSubcategory));
        return subcategoryMapper.toSubcategoryDto(foundSubcategory);
    }

    @Override
    public List<SubcategoryDto> findSubcategories(Pageable pageable) {
        log.info("IN: trying to find subcategories. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<Subcategory> subcategories = subcategoryRepository.findAll(pageable).getContent();
        log.info("OUT: {} subcategories found", subcategories.size());
        return subcategoryMapper.toSubcategoryDtos(subcategories);
    }

    @Override
    public SubcategoryDto updateSubcategory(Long subcategoryId, SubcategoryUpdateDto subcategoryUpdateDto) {
        log.info("IN: trying to update a subcategory with id = {} by new details = {}",
                subcategoryId, getAsString(subcategoryUpdateDto));
        Subcategory existedSubcategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
        Subcategory updatedSubcategory = subcategoryRepository.save(
                subcategoryMapper.updateSubcategory(existedSubcategory, subcategoryUpdateDto));
        log.info("OUT: the subcategory updated successfully. The updated subcategory details = {}",
                getAsString(updatedSubcategory));
        return subcategoryMapper.toSubcategoryDto(updatedSubcategory);
    }

    @Override
    public void deleteSubcategoryById(Long subcategoryId) {
        log.info("IN: trying to delete a subcategory by id = {}", subcategoryId);
        subcategoryRepository.deleteById(subcategoryId);
        log.info("OUT: the subcategory with id = {} deleted successfully", subcategoryId);
    }
}