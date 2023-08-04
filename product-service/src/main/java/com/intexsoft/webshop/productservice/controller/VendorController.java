package com.intexsoft.webshop.productservice.controller;

import com.intexsoft.webshop.productservice.dto.vendor.VendorCreateDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorUpdateDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.service.VendorService;
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
@RequestMapping("/vendor")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorDto> createVendor(@RequestBody @Valid VendorCreateDto vendorCreateDto) {
        log.info("IN: request to create a new vendor received. Request body = {}", getAsString(vendorCreateDto));
        VendorDto createdVendor = vendorService.createVendor(vendorCreateDto);
        log.info("OUT: new vendor created successfully. Response body = {}", getAsString(createdVendor));
        return ResponseEntity.ok(createdVendor);
    }

    @GetMapping
    public ResponseEntity<List<VendorDto>> findVendors(@PageableDefault(size = MAX_VALUE) Pageable pageable) {
        log.info("IN: request to find vendors received. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<VendorDto> vendorDtos = vendorService.findVendors(pageable);
        log.info("OUT: {} vendors found", vendorDtos.size());
        return ResponseEntity.ok(vendorDtos);
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorDto> findVendorById(@PathVariable @Positive Long vendorId) {
        log.info("IN: request to find a vendor by id = {} received", vendorId);
        VendorDto vendorDto = vendorService.findVendorById(vendorId);
        log.info("OUT: the vendor with id = {} found successfully. Response body = {}",
                vendorId, getAsString(vendorDto));
        return ResponseEntity.ok(vendorDto);
    }

    @PutMapping("/{vendorId}")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable @Positive Long vendorId,
                                                  @RequestBody @Valid VendorUpdateDto vendorUpdateDto) {
        log.info("IN: request to update a vendor with id = {} received. Request body = {}",
                vendorId, getAsString(vendorUpdateDto));
        VendorDto updatedVendorDto = vendorService.updateVendor(vendorId, vendorUpdateDto);
        log.info("OUT: the vendor with id = {} updated successfully. Response body = {}",
                vendorId, getAsString(updatedVendorDto));
        return ResponseEntity.ok(updatedVendorDto);
    }

    @DeleteMapping("/{vendorId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable @Positive Long vendorId) {
        log.info("IN: request to delete a category by id = {} received", vendorId);
        vendorService.deleteVendorById(vendorId);
        log.info("OUT: the vendor with id = {} deleted successfully", vendorId);
        return ResponseEntity.noContent().build();
    }
}