package com.intexsoft.webshop.productservicekt.service

import com.intexsoft.webshop.productservicekt.dto.vendor.VendorCreateDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorUpdateDto
import org.springframework.data.domain.Pageable

interface VendorService {
    fun createVendor(vendorCreateDto: VendorCreateDto): VendorDto
    fun findVendorById(vendorId: Long): VendorDto
    fun findVendors(pageable: Pageable): List<VendorDto>
    fun updateVendor(vendorId: Long, vendorUpdateDto: VendorUpdateDto): VendorDto
    fun deleteVendorById(vendorId: Long)
}