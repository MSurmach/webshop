package com.intexsoft.webshop.productservicekt.controller

import com.intexsoft.webshop.productservicekt.dto.vendor.VendorCreateDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorDto
import com.intexsoft.webshop.productservicekt.dto.vendor.VendorUpdateDto
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.mapper.VendorMapper
import com.intexsoft.webshop.productservicekt.service.VendorService
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vendor")
@Validated
class VendorController(
    private val vendorService: VendorService,
    private val vendorMapper: VendorMapper
) {
    @PostMapping
    fun createVendor(@RequestBody vendorCreateDto: @Valid VendorCreateDto): ResponseEntity<VendorDto> {
        log.info("IN: request to create a new vendor received. Request body = $vendorCreateDto")
        val createdVendor: VendorDto = vendorService.createVendor(vendorCreateDto)
        log.info("OUT: new vendor created successfully. Response body = $createdVendor")
        return ResponseEntity.ok(createdVendor)
    }

    @GetMapping
    fun findVendors(@PageableDefault(size = Int.MAX_VALUE) pageable: Pageable): ResponseEntity<List<VendorDto>> {
        log.info("IN: request to find vendors received. Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}")
        val vendorDtos: List<VendorDto> = vendorService.findVendors(pageable)
        log.info("OUT: ${vendorDtos.size} vendors found")
        return ResponseEntity.ok(vendorDtos)
    }

    @GetMapping("/{vendorId}")
    fun findVendorById(@PathVariable vendorId: @Positive Long): ResponseEntity<VendorDto> {
        log.info("IN: request to find a vendor by id = $vendorId received")
        val vendorDto: VendorDto = vendorService.findVendorById(vendorId)
        log.info("OUT: the vendor with id = $vendorId found successfully. Response body = $vendorDto")
        return ResponseEntity.ok(vendorDto)
    }

    @PutMapping("/{vendorId}")
    fun updateVendor(
        @PathVariable vendorId: @Positive Long,
        @RequestBody vendorUpdateDto: @Valid VendorUpdateDto
    ): ResponseEntity<VendorDto> {
        log.info("IN: request to update a vendor with id = $vendorId received. Request body = $vendorUpdateDto")
        val updatedVendorDto: VendorDto = vendorService.updateVendor(vendorId, vendorUpdateDto)
        log.info("OUT: the vendor with id = $vendorId updated successfully. Response body = $updatedVendorDto")
        return ResponseEntity.ok(updatedVendorDto)
    }

    @DeleteMapping("/{vendorId}")
    fun deleteProductById(@PathVariable vendorId: @Positive Long): ResponseEntity<Unit> {
        log.info("IN: request to delete a category by id = $vendorId received")
        vendorService.deleteVendorById(vendorId)
        log.info("OUT: the vendor with id = $vendorId deleted successfully")
        return ResponseEntity.noContent().build()
    }
}