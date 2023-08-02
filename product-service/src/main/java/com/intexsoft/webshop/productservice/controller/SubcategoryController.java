package com.intexsoft.webshop.productservice.controller;

import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryCreateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryUpdateDto;
import com.intexsoft.webshop.productservice.service.SubcategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/{categoryId}/subcategory")
@RequiredArgsConstructor
@Slf4j
@Validated
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @PostMapping
    public ResponseEntity<SubcategoryDto> createSubcategory(@RequestBody @Valid SubcategoryCreateDto subcategoryCreateDto,
                                                            @PathVariable @Positive Long categoryId) {
        SubcategoryDto createdSubCategory = subcategoryService.createSubcategory(subcategoryCreateDto, categoryId);
        return ResponseEntity.ok(createdSubCategory);
    }

    @GetMapping
    public ResponseEntity<List<SubcategoryDto>> findAllSubcategories(@PathVariable @Positive Long categoryId) {
        List<SubcategoryDto> subcategoryDtos = subcategoryService.findAllSubcategoriesBelongToCategory(categoryId);
        return ResponseEntity.ok(subcategoryDtos);
    }

    @GetMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryDto> findSubcategoryById(@PathVariable @Positive Long subcategoryId) {
        SubcategoryDto subcategoryDto = subcategoryService.findSubcategoryById(subcategoryId);
        return ResponseEntity.ok(subcategoryDto);
    }

    @PutMapping("/{subcategoryId}")
    public ResponseEntity<SubcategoryDto> updateSubcategory(@PathVariable @Positive Long subcategoryId,
                                                            @RequestBody @Valid SubcategoryUpdateDto subcategoryUpdateDto) {
        SubcategoryDto updatedSubcategoryDto = subcategoryService.updateSubcategory(subcategoryId, subcategoryUpdateDto);
        return ResponseEntity.ok(updatedSubcategoryDto);
    }

    @DeleteMapping("/{subcategoryId}")
    public ResponseEntity<Void> deleteSubcategoryById(@PathVariable @Positive Long subcategoryId) {
        subcategoryService.deleteSubcategoryById(subcategoryId);
        return ResponseEntity.noContent().build();
    }
}