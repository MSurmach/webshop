package com.intexsoft.webshop.productservicekt.service.impl

import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueCreateDto
import com.intexsoft.webshop.productservicekt.dto.attributevalue.AttributeValueUpdateDto
import com.intexsoft.webshop.productservicekt.dto.image.ImageUpdateDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductCreateDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductDto
import com.intexsoft.webshop.productservicekt.dto.product.ProductUpdateDto
import com.intexsoft.webshop.productservicekt.exception.notfound.*
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.mapper.ImageMapper
import com.intexsoft.webshop.productservicekt.mapper.ProductEventMapper
import com.intexsoft.webshop.productservicekt.mapper.ProductMapper
import com.intexsoft.webshop.productservicekt.model.*
import com.intexsoft.webshop.productservicekt.producer.ProductEventProducer
import com.intexsoft.webshop.productservicekt.repository.AttributeRepository
import com.intexsoft.webshop.productservicekt.repository.ProductRepository
import com.intexsoft.webshop.productservicekt.repository.SubcategoryRepository
import com.intexsoft.webshop.productservicekt.repository.VendorRepository
import com.intexsoft.webshop.productservicekt.repository.spec.ProductSpecifications
import com.intexsoft.webshop.productservicekt.service.ProductService
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper,
    private val productEventMapper: ProductEventMapper,
    private val imageMapper: ImageMapper,
    private val productEventProducer: ProductEventProducer,
    private val vendorRepository: VendorRepository,
    private val subcategoryRepository: SubcategoryRepository,
    private val attributeRepository: AttributeRepository
) : ProductService {

    @Transactional
    override fun createProduct(productCreateDto: ProductCreateDto): ProductDto {
        log.info("IN: trying to save a new product. Product details = $productCreateDto")
        val vendorId: Long = productCreateDto.vendorId
        val vendor: Vendor = vendorRepository.findByIdOrNull(vendorId) ?: throw VendorNotFoundException(vendorId)
        val subcategoryId: Long = productCreateDto.subcategoryId
        val subcategory: Subcategory =
            subcategoryRepository.findByIdOrNull(subcategoryId) ?: throw SubcategoryNotFoundException(subcategoryId)
        val attributeValues: List<AttributeValue> = createAttributeValues(productCreateDto)
        val newProduct: Product = productMapper.toProduct(productCreateDto, vendor, subcategory)
        productCreateDto.imageCreateDtos!!.forEach { newProduct.addImage(imageMapper.toImage(it)) }
        attributeValues.forEach { newProduct.addAttributeValue(it) }
        val savedProduct: Product = productRepository.save(newProduct)
        log.info("OUT: the product saved successfully. The saved product details = $savedProduct")
        productEventProducer.produceProductEventCreated(productEventMapper.toProductEventCreated(savedProduct))
        return productMapper.toProductDto(savedProduct)
    }

    override fun findProductById(productId: Long): ProductDto {
        log.info("IN: trying to find a product by id = productId")
        val foundProduct: Product =
            productRepository.findByIdOrNull(productId) ?: throw ProductNotFoundException(productId)
        log.info("OUT: the product with id = $productId found successfully. Found product details = $foundProduct")
        return productMapper.toProductDto(foundProduct)
    }

    override fun findProducts(pageable: Pageable): List<ProductDto> {
        log.info("IN: trying to find products. Page size = ${pageable.pageSize}, page number = ${pageable.pageNumber}")
        val products: List<Product> =
            productRepository.findAll(ProductSpecifications.findAllProducts(), pageable).content
        log.info("OUT: $products.size products found")
        return productMapper.toProductDtos(products)
    }

    @Transactional
    override fun updateProduct(productId: Long, productUpdateDto: ProductUpdateDto): ProductDto {
        log.info("IN: trying to update a product with id = $productId by new details = $productUpdateDto")
        val existedProduct: Product =
            productRepository.findByIdOrNull(productId) ?: throw ProductNotFoundException(productId)
        val vendorForUpdate: Vendor? = getVendorForUpdate(productUpdateDto)
        val subcategoryForUpdate: Subcategory? = getSubcategoryForUpdate(productUpdateDto)
        var product: Product = productMapper.updateProduct(
            existedProduct, productUpdateDto, vendorForUpdate,
            subcategoryForUpdate
        )
        product = updateProductImages(product, productUpdateDto)
        product = updateAttributeValues(product, productUpdateDto)
        val updatedProduct: Product = productRepository.save(product)
        log.info("OUT: the product updated successfully. The updated product details = $updatedProduct")
        if (existedProduct.name != updatedProduct.name) productEventProducer.produceProductEventUpdated(
            productEventMapper.toProductEventUpdated(updatedProduct)
        )
        return productMapper.toProductDto(updatedProduct)
    }

    @Transactional
    override fun deleteProductById(productId: Long) {
        log.info("IN: trying to delete a product by id = $productId")
        productRepository.deleteById(productId)
        log.info("OUT: the product with id = $productId deleted successfully")
        productEventProducer.produceProductEventDeleted(productEventMapper.toProductEventDeleted(productId))
    }

    private fun createAttributeValues(productCreateDto: ProductCreateDto): List<AttributeValue> {
        val attributeValueCreateDtos: List<AttributeValueCreateDto> = productCreateDto.attributeValueCreateDtos!!
        val attributeIds = getAttributeIds(attributeValueCreateDtos)
        val attributes: List<Attribute> = attributeRepository.findAllById(attributeIds)
        return toAttributeValues(attributes, attributeValueCreateDtos)
    }

    private fun getAttributeIds(attributes: List<AttributeValueCreateDto>): List<Long> =
        attributes.map { it.attributeId }

    private fun toAttributeValues(
        attributes: List<Attribute>,
        attributeValueCreateDtos: List<AttributeValueCreateDto>
    ): List<AttributeValue> {
        val attributeIdAttributeMap: Map<Long, Attribute> = attributes.associateBy { it.id }
        return attributeValueCreateDtos.map {
            AttributeValue(value = it.value!!, attribute = attributeIdAttributeMap[it.attributeId])
        }
    }

    private fun getVendorForUpdate(productUpdateDto: ProductUpdateDto): Vendor? {
        val vendorId: Long = productUpdateDto.vendorId ?: return null
        return vendorRepository.findByIdOrNull(productUpdateDto.vendorId) ?: throw VendorNotFoundException(vendorId)
    }

    private fun getSubcategoryForUpdate(productUpdateDto: ProductUpdateDto): Subcategory? {
        val subcategoryId: Long = productUpdateDto.subcategoryId ?: return null
        return subcategoryRepository.findByIdOrNull(subcategoryId) ?: throw SubcategoryNotFoundException(subcategoryId)
    }

    private fun updateProductImages(productForImagesUpdate: Product, productUpdateDto: ProductUpdateDto): Product {
        val imageUpdateDtos: List<ImageUpdateDto> = productUpdateDto.imageUpdateDtos ?: return productForImagesUpdate
        val productImageIdImageMap: Map<Long, Image>? = productForImagesUpdate.images?.associateBy { it.id }
        imageUpdateDtos.forEach {
            val imageId: Long? = it.id
            if (imageId == null) {
                productForImagesUpdate.addImage(imageMapper.toImage(it))
                return@forEach
            }
            val image: Image = productImageIdImageMap?.get(imageId) ?: throw ImageNotFoundException(imageId)
            productForImagesUpdate.removeImage(image)
            val filePath: String? = it.filePath
            if (filePath != null) {
                image.filePath = filePath
                productForImagesUpdate.addImage(image)
            }
        }
        return productForImagesUpdate
    }

    private fun updateAttributeValues(
        productForAttributesUpdate: Product,
        productUpdateDto: ProductUpdateDto
    ): Product {
        val attributeValueUpdateDtos: List<AttributeValueUpdateDto> =
            productUpdateDto.attributeValueUpdateDtos ?: return productForAttributesUpdate
        val productAttributeIdAttributeValueMap: Map<Long, AttributeValue>? =
            productForAttributesUpdate.attributeValues?.associateBy { it.id }
        attributeValueUpdateDtos.forEach {
            val attributeId: Long = it.attributeId
            val attributeValue: AttributeValue? = productAttributeIdAttributeValueMap?.get(attributeId)
            if (attributeValue == null) {
                val existedAttribute: Attribute = attributeRepository.findByIdOrNull(attributeId)
                    ?: throw AttributeNotFoundException(attributeId)
                val newAttributeValue: AttributeValue = AttributeValue(value = it.value!!, attribute = existedAttribute)
                productForAttributesUpdate.addAttributeValue(newAttributeValue)
                return@forEach
            }
            productForAttributesUpdate.removeAttributeValue(attributeValue)
            if (it.value != null) {
                attributeValue.value = it.value
                productForAttributesUpdate.addAttributeValue(attributeValue)
            }
        }
        return productForAttributesUpdate
    }
}