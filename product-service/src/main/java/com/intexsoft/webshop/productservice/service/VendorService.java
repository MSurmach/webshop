package com.intexsoft.webshop.productservice.service;

import com.intexsoft.webshop.productservice.dto.vendor.VendorCreateDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorUpdateDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {
    VendorDto createVendor(VendorCreateDto vendorCreateDto);

    VendorDto findVendorById(Long vendorId);

    List<VendorDto> findVendors(Pageable pageable);

    VendorDto updateVendor(Long vendorId, VendorUpdateDto vendorUpdateDto);

    void deleteVendorById(Long vendorId);
}