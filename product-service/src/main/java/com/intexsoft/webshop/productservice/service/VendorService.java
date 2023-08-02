package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.vendor.CreateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.UpdateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {
    VendorDto createVendor(CreateVendorDto createVendorDto);

    VendorDto findVendorById(Long id);

    List<VendorDto> findVendors(Pageable pageable);

    VendorDto updateVendor(Long id, UpdateVendorDto updateVendorDto);

    void deleteVendorById(Long id);
}