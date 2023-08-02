package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.vendor.CreateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.UpdateVendorDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.exception.ResourceNotFoundException;
import com.intexsoft.webshop.productservice.exception.SuchResourceExistsException;
import com.intexsoft.webshop.productservice.mapper.VendorMapper;
import com.intexsoft.webshop.productservice.model.Vendor;
import com.intexsoft.webshop.productservice.repository.VendorRepository;
import com.intexsoft.webshop.productservice.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    @Transactional
    public VendorDto createVendor(CreateVendorDto createVendorDto) {
        String name = createVendorDto.getName();
        if (vendorRepository.findByNameIgnoreCase(name).isPresent())
            throw new SuchResourceExistsException("Unable to save a new vendor," +
                    " because the vendor with the same name exists");
        Vendor savedVendor = vendorRepository.save(vendorMapper.toVendor(createVendorDto));
        return vendorMapper.toVendorDto(savedVendor);
    }

    @Override
    public VendorDto findVendorById(Long id) {
        Vendor foundVendor = findById(id);
        return vendorMapper.toVendorDto(foundVendor);
    }

    @Override
    public List<VendorDto> findVendors(Pageable pageable) {
        Page<Vendor> vendorPage = vendorRepository.findAll(pageable);
        return vendorMapper.toVendorDtos(vendorPage.getContent());
    }

    @Override
    public VendorDto updateVendor(Long id, UpdateVendorDto updateVendorDto) {
        Vendor existedVendor = findById(id);
        Vendor updatedVendor = vendorRepository.save(vendorMapper.updateVendor(existedVendor, updateVendorDto));
        return vendorMapper.toVendorDto(updatedVendor);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private Vendor findById(Long id) {
        Vendor foundVendor = vendorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Vendor with id= " + id + " not found"));
        return foundVendor;
    }
}