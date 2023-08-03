package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.attribute.AttributeDto;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import com.intexsoft.webshop.productservice.dto.subcategory.SubcategoryDto;
import com.intexsoft.webshop.productservice.dto.vendor.VendorDto;
import com.intexsoft.webshop.productservice.exception.ResourceNotFoundException;
import com.intexsoft.webshop.productservice.mapper.ProductMapper;
import com.intexsoft.webshop.productservice.model.Product;
import com.intexsoft.webshop.productservice.producer.ProductEventProducer;
import com.intexsoft.webshop.productservice.repository.ProductRepository;
import com.intexsoft.webshop.productservice.service.AttributeService;
import com.intexsoft.webshop.productservice.service.ProductService;
import com.intexsoft.webshop.productservice.service.SubcategoryService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final VendorService vendorService;
    private final SubcategoryService subcategoryService;
    private final ProductEventProducer productEventProducer;
    private final AttributeService attributeService;

    @Override
    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        log.info("IN: trying to save a new product. Product details = {}",
                getAsString(productCreateDto));
        VendorDto vendorDto = vendorService.findVendorById(productCreateDto.getVendorId());
        Long subcategoryId = productCreateDto.getSubcategoryId();
        SubcategoryDto subcategoryDto = subcategoryService.findSubcategoryById(subcategoryId);
        List<Long> attributeIds = productCreateDto.getAttributeValueCreateDtos().stream()
                .map(AttributeValueCreateDto::getAttributeId)
                .toList();
        List<AttributeDto> attributeDtos = attributeService.findAllByIdInAndSubcategoryId(attributeIds, subcategoryId);
        Product product = productMapper.toProduct(productCreateDto, vendorDto, subcategoryDto, attributeDtos);
        Product savedProduct = productRepository.save(product);
        log.info("OUT: the product saved successfully. The saved product details = {}",
                getAsString(savedProduct));
        productEventProducer.produceProductEventCreated(productMapper.toProductEventCreated(savedProduct));
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto findProductById(Long productId) {
        log.info("IN: trying to find a product by id = {}", productId);
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("The product with id = " + productId + " not found"));
        log.info("OUT: the product with id = {} found successfully. Found product details = {}",
                productId, getAsString(foundProduct));
        return productMapper.toProductDto(foundProduct);
    }

    @Override
    public List<ProductDto> findProducts(Pageable pageable) {
        log.info("IN: trying to find products. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<Product> products = productRepository.findAll(pageable).getContent();
        log.info("OUT: {} products found", products.size());
        return productMapper.toProductDtos(products);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long productId, ProductUpdateDto productUpdateDto) {
        log.info("IN: trying to update a product with id = {} by new details = {}",
                productId, getAsString(productUpdateDto));
        ProductDto existedProductDto = findProductById(productId);
        Product updatedProduct = productRepository.save(productMapper.toProduct(productUpdateDto));
        log.info("OUT: the product updated successfully. The updated product details = {}", getAsString(updatedProduct));
        if (!existedProductDto.getName().equals(updatedProduct.getName()))
            productEventProducer.produceProductEventUpdated(productMapper.toProductEventUpdated(updatedProduct));
        return productMapper.toProductDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
        productEventProducer.produceProductEventDeleted(productMapper.toProductEventDeleted(productId));
    }
}