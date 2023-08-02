package com.intexsoft.webshop.productservice.controller;

import com.intexsoft.webshop.productservice.dto.vendor.CreateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.UpdateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.service.VendorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorDto> createVendor(@RequestBody @Valid CreateVendorDto createVendorDto) {
        log.info("IN: request to create a new vendor received. Request body = {}", getAsString(createVendorDto));
        VendorDto createdVendor = vendorService.createVendor(createVendorDto);
        log.info("OUT: new vendor created successfully. Response body = {}", getAsString(createdVendor));
        return ResponseEntity.ok(createdVendor);
    }

    @GetMapping
    public ResponseEntity<List<VendorDto>> findVendor(Pageable pageable) {
        List<VendorDto> vendorDtos = vendorService.findVendors(pageable);
        return ResponseEntity.ok(vendorDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDto> findVendorById(@PathVariable @Positive Long id) {
        VendorDto vendorDto = vendorService.findVendorById(id);
        return ResponseEntity.ok(vendorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable @Positive Long id,
                                                  @RequestBody @Valid UpdateVendorDto updateVendorDto) {
        VendorDto updatedVendorDto = vendorService.updateVendor(id, updateVendorDto);
        return ResponseEntity.ok(updatedVendorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable @Positive Long id) {
        vendorService.deleteVendorById(id);
        return ResponseEntity.noContent().build();
    }
}