package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.vendor.VendorCreateDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorUpdateDto;
import com.intexsoft.webshop.productservice.exception.conflict409.VendorExistsException;
import com.intexsoft.webshop.productservice.exception.notfound404.VendorNotFoundException;
import com.intexsoft.webshop.productservice.mapper.VendorMapper;
import com.intexsoft.webshop.productservice.model.Vendor;
import com.intexsoft.webshop.productservice.repository.VendorRepository;
import com.intexsoft.webshop.productservice.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    @Transactional
    public VendorDto createVendor(VendorCreateDto vendorCreateDto) {
        String name = vendorCreateDto.getName();
        log.info("IN: trying to save a new vendor. New vendor details = {}", getAsString(vendorCreateDto));
        vendorRepository.findByNameIgnoreCase(name)
                .ifPresent(vendor -> {
                    throw new VendorExistsException(vendor.getName());
                });
        Vendor savedVendor = vendorRepository.save(vendorMapper.toVendor(vendorCreateDto));
        log.info("OUT: the vendor saved successfully. The saved vendor details = {}", getAsString(savedVendor));
        return vendorMapper.toVendorDto(savedVendor);
    }

    @Override
    public VendorDto findVendorById(Long vendorId) {
        log.info("IN: trying to find a vendor by id = {}", vendorId);
        Vendor foundVendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorNotFoundException(vendorId));
        log.info("OUT: the vendor with id = {} found successfully. Found vendor details = {}",
                vendorId, getAsString(foundVendor));
        return vendorMapper.toVendorDto(foundVendor);
    }

    @Override
    public List<VendorDto> findVendors(Pageable pageable) {
        log.info("IN: trying to find vendors. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<Vendor> vendors = vendorRepository.findAll(pageable).getContent();
        log.info("OUT: {} vendors found", vendors.size());
        return vendorMapper.toVendorDtos(vendors);
    }

    @Override
    public VendorDto updateVendor(Long vendorId, VendorUpdateDto vendorUpdateDto) {
        log.info("IN: trying to update a vendor with id = {} by new details = {}",
                vendorId, getAsString(vendorUpdateDto));
        Vendor existedVendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorNotFoundException(vendorId));
        Vendor updatedVendor = vendorRepository.save(vendorMapper.updateVendor(existedVendor, vendorUpdateDto));
        log.info("OUT: the vendor updated successfully. The updated vendor details = {}", getAsString(updatedVendor));
        return vendorMapper.toVendorDto(updatedVendor);
    }

    @Override
    public void deleteVendorById(Long vendorId) {
        log.info("IN: trying to delete a vendor by id = {}", vendorId);
        vendorRepository.deleteById(vendorId);
        log.info("OUT: the vendor with id = {} deleted successfully", vendorId);
    }
}