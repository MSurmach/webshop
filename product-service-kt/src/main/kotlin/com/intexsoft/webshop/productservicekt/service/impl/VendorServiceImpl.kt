package com.intexsoft.webshop.productservicekt.service.impl

import com.intexsoft.webshop.productservicekt.dto.vendor.VendorCreateDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorUpdateDto
import com.intexsoft.webshop.productservicekt.exception.conflict.VendorExistsException
import com.intexsoft.webshop.productservicekt.exception.notfound.VendorNotFoundException
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.mapper.VendorMapper
import com.intexsoft.webshop.productservicekt.model.Vendor
import com.intexsoft.webshop.productservicekt.repository.VendorRepository
import com.intexsoft.webshop.productservicekt.service.VendorService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class VendorServiceImpl(
    private val vendorRepository: VendorRepository,
    private val vendorMapper: VendorMapper
) : VendorService {
    @Transactional
    override fun createVendor(vendorCreateDto: VendorCreateDto): VendorDto {
        val name: String = vendorCreateDto.name
        log.info("IN: trying to save a new vendor. New vendor details = $vendorCreateDto")
        if (vendorRepository.findByNameIgnoreCase(name) != null) throw VendorExistsException(name)
        val savedVendor: Vendor = vendorRepository.save(vendorMapper.toVendor(vendorCreateDto))
        log.info("OUT: the vendor saved successfully. The saved vendor details = $savedVendor")
        return vendorMapper.toVendorDto(savedVendor)
    }

    override fun findVendorById(vendorId: Long): VendorDto {
        log.info("IN: trying to find a vendor by id = $vendorId")
        val foundVendor: Vendor = vendorRepository.findByIdOrNull(vendorId) ?: throw VendorNotFoundException(vendorId)
        log.info("OUT: the vendor with id = $vendorId found successfully. Found vendor details = $foundVendor")
        return vendorMapper.toVendorDto(foundVendor)
    }

    override fun findVendors(pageable: Pageable): List<VendorDto> {
        log.info("IN: trying to find vendors. Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}")
        val vendors: List<Vendor> = vendorRepository.findAll(pageable).content
        log.info("OUT: ${vendors.size} vendors found")
        return vendorMapper.toVendorDtos(vendors)
    }

    override fun updateVendor(vendorId: Long, vendorUpdateDto: VendorUpdateDto): VendorDto {
        log.info("IN: trying to update a vendor with id = $vendorId by new details = $vendorUpdateDto")
        val existedVendor: Vendor = vendorRepository.findByIdOrNull(vendorId) ?: throw VendorNotFoundException(vendorId)
        val updatedVendor: Vendor = vendorRepository.save(vendorMapper.updateVendor(existedVendor, vendorUpdateDto))
        log.info("OUT: the vendor updated successfully. The updated vendor details = $updatedVendor")
        return vendorMapper.toVendorDto(updatedVendor)
    }

    override fun deleteVendorById(vendorId: Long) {
        log.info("IN: trying to delete a vendor by id = $vendorId")
        vendorRepository.deleteById(vendorId)
        log.info("OUT: the vendor with id = $vendorId deleted successfully")
    }
}