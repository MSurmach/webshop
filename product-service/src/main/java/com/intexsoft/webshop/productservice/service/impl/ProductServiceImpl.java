package com.intexsoft.webshop.productservice.service.impl;

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
import com.intexsoft.webshop.productservice.service.ProductService;
import com.intexsoft.webshop.productservice.service.SubcategoryService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final VendorService vendorService;
    private final SubcategoryService subcategoryService;
    private final ProductEventProducer productEventProducer;

    @Override
    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        VendorDto vendorDto = vendorService.findVendorById(productCreateDto.getVendorId());
        SubcategoryDto subcategoryDto = subcategoryService.findSubcategoryById(productCreateDto.getSubcategoryId());
        Product product = productMapper.toProduct(productCreateDto, vendorDto, subcategoryDto);
        Product savedProduct = productRepository.save(product);
        productEventProducer.produceProductEventCreated(productMapper.toProductEventCreated(savedProduct));
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto findProductById(Long productId) {
        Product foundProduct = findById(productId);
        return productMapper.toProductDto(foundProduct);
    }

    @Override
    public List<ProductDto> findProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productMapper.toProductDtos(productPage.getContent());
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long productId, ProductUpdateDto productUpdateDto) {
        Product existedProduct = findById(productId);
        Product updatedProduct = productRepository.save(
                productMapper.updateProduct(existedProduct, productUpdateDto));
        if (!existedProduct.getName().equals(updatedProduct.getName()))
            productEventProducer.produceProductEventUpdated(productMapper.toProductEventUpdated(updatedProduct));
        return productMapper.toProductDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
        productEventProducer.produceProductEventDeleted(productMapper.toProductEventDeleted(productId));
    }

    private Product findById(Long productId) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("The product with id = " + productId + " not found"));
        return foundProduct;
    }
}