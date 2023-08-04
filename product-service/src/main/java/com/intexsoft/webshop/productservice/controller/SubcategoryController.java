package com.intexsoft.webshop.productservice.controller;

import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import com.intexsoft.webshop.productservice.service.SubcategoryService;
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

@RestController
@RequestMapping("/subcategory")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @PostMapping
    public ResponseEntity<SubcategoryDto> createSubcategory(@RequestBody @Valid
                                                            SubcategoryCreateDto subcategoryCreateDto) {
        log.info("IN: request to create a new subcategory received. Request body = {}",
                getAsString(subcategoryCreateDto));
        SubcategoryDto createdSubCategoryDto = subcategoryService.createSubcategory(subcategoryCreateDto);
        log.info("OUT: new subcategory created successfully. Response body = {}",
                getAsString(createdSubCategoryDto));
        return ResponseEntity.ok(createdSubCategoryDto);
    }

    @GetMapping
    public ResponseEntity<List<SubcategoryDto>> findAllSubcategories(@PageableDefault(size = Integer.MAX_VALUE)
                                                                     Pageable pageable) {
        log.info("IN: request to find subcategories received. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<SubcategoryDto> subcategoryDtos = subcategoryService.findSubcategories(pageable);
        log.info("OUT: {} subcategories found", subcategoryDtos.size());
        return ResponseEntity.ok(subcategoryDtos);
    }

    @GetMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryDto> findSubcategoryById(@PathVariable @Positive Long subcategoryId) {
        log.info("IN: request to find a subcategory by id = {} received", subcategoryId);
        SubcategoryDto subcategoryDto = subcategoryService.findSubcategoryById(subcategoryId);
        log.info("OUT: the subcategory with id = {} found successfully. Response body = {}",
                subcategoryId, getAsString(subcategoryDto));
        return ResponseEntity.ok(subcategoryDto);
    }

    @PutMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryDto> updateSubcategory(@PathVariable @Positive Long subcategoryId,
                                                            @RequestBody @Valid SubcategoryUpdateDto subcategoryUpdateDto) {
        log.info("IN: request to update a subcategory with id = {} received. Request body = {}",
                subcategoryId, getAsString(subcategoryUpdateDto));
        SubcategoryDto updatedSubcategoryDto = subcategoryService.updateSubcategory(subcategoryId, subcategoryUpdateDto);
        log.info("OUT: the subcategory with id = {} updated successfully. Response body = {}",
                subcategoryId, getAsString(updatedSubcategoryDto));
        return ResponseEntity.ok(updatedSubcategoryDto);
    }

    @DeleteMapping("/{subcategoryId}")
    public ResponseEntity<Void> deleteSubcategoryById(@PathVariable @Positive Long subcategoryId) {
        log.info("IN: request to delete a subcategory by id = {} received", subcategoryId);
        subcategoryService.deleteSubcategoryById(subcategoryId);
        log.info("OUT: the subcategory with id = {} deleted successfully", subcategoryId);
        return ResponseEntity.noContent().build();
    }
}