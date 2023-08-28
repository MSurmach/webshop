package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueCreateDto;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueUpdateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageUpdateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import com.intexsoft.webshop.productservice.exception.notfound404.*;
import com.intexsoft.webshop.productservice.mapper.ImageMapper;
import com.intexsoft.webshop.productservice.mapper.ProductEventMapper;
import com.intexsoft.webshop.productservice.mapper.ProductMapper;
import com.intexsoft.webshop.productservice.model.*;
import com.intexsoft.webshop.productservice.producer.ProductEventProducer;
import com.intexsoft.webshop.productservice.repository.AttributeRepository;
import com.intexsoft.webshop.productservice.repository.ProductRepository;
import com.intexsoft.webshop.productservice.repository.SubcategoryRepository;
import com.intexsoft.webshop.productservice.repository.VendorRepository;
import com.intexsoft.webshop.productservice.repository.spec.ProductSpecifications;
import com.intexsoft.webshop.productservice.service.ProductService;
import com.intexsoft.webshop.productservice.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductEventMapper productEventMapper;
    private final ImageMapper imageMapper;
    private final ProductEventProducer productEventProducer;
    private final VendorRepository vendorRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AttributeRepository attributeRepository;

    @Override
    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        log.info("IN: trying to save a new product. Product details = {}",
                JsonUtils.getAsString(productCreateDto));
        Long vendorId = productCreateDto.getVendorId();
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorNotFoundException(vendorId));
        Long subcategoryId = productCreateDto.getSubcategoryId();
        Subcategory subcategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
        List<AttributeValue> attributeValues = createAttributeValues(productCreateDto);
        Product product = productMapper.toProduct(productCreateDto, vendor, subcategory, attributeValues);
        Product savedProduct = productRepository.save(product);
        log.info("OUT: the product saved successfully. The saved product details = {}",
                JsonUtils.getAsString(savedProduct));
        productEventProducer.produceProductEventCreated(productEventMapper.toProductEventCreated(savedProduct));
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto findProductById(Long productId) {
        log.info("IN: trying to find a product by id = {}", productId);
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(productId));
        log.info("OUT: the product with id = {} found successfully. Found product details = {}",
                productId, JsonUtils.getAsString(foundProduct));
        return productMapper.toProductDto(foundProduct);
    }

    @Override
    public List<ProductDto> findProducts(Pageable pageable) {
        log.info("IN: trying to find products. Page size = {}, page number = {}",
                pageable.getPageSize(), pageable.getPageNumber());
        List<Product> products = productRepository
                .findAll(ProductSpecifications.findAllProducts(), pageable)
                .getContent();
        log.info("OUT: {} products found", products.size());
        return productMapper.toProductDtos(products);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long productId, ProductUpdateDto productUpdateDto) {
        log.info("IN: trying to update a product with id = {} by new details = {}",
                productId, JsonUtils.getAsString(productUpdateDto));
        Product existedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Vendor vendorForUpdate = getVendorForUpdate(productUpdateDto);
        Subcategory subcategoryForUpdate = getSubcategoryForUpdate(productUpdateDto);
        Product product = productMapper.updateProduct(existedProduct, productUpdateDto, vendorForUpdate,
                subcategoryForUpdate);
        product = updateProductImages(product, productUpdateDto);
        product = updateAttributeValues(product, productUpdateDto);
        Product updatedProduct = productRepository.save(product);
        log.info("OUT: the product updated successfully. The updated product details = {}",
                JsonUtils.getAsString(updatedProduct));
        if (!existedProduct.getName().equals(updatedProduct.getName()))
            productEventProducer.produceProductEventUpdated(productEventMapper.toProductEventUpdated(updatedProduct));
        return productMapper.toProductDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        log.info("IN: trying to delete a product by id = {}", productId);
        productRepository.deleteById(productId);
        log.info("OUT: the product with id = {} deleted successfully", productId);
        productEventProducer.produceProductEventDeleted(productEventMapper.toProductEventDeleted(productId));
    }

    private List<AttributeValue> createAttributeValues(ProductCreateDto productCreateDto) {
        List<AttributeValueCreateDto> attributeValueCreateDtos = productCreateDto.getAttributeValueCreateDtos();
        if (Objects.isNull(attributeValueCreateDtos)) return null;
        List<Long> attributeIds = getAttributeIds(attributeValueCreateDtos);
        List<Attribute> attributes = attributeRepository.findAllById(attributeIds);
        return toAttributeValues(attributes,
                productCreateDto.getAttributeValueCreateDtos());
    }

    private List<Long> getAttributeIds(List<AttributeValueCreateDto> attributes) {
        return attributes.stream()
                .map(AttributeValueCreateDto::getAttributeId)
                .toList();
    }

    private List<AttributeValue> toAttributeValues(List<Attribute> attributes,
                                                   List<AttributeValueCreateDto> attributeValueCreateDtos) {
        Map<Long, Attribute> attributeIdLabelMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getId, Function.identity()));
        return attributeValueCreateDtos.stream()
                .map(attributeValueCreateDto -> {
                    AttributeValue attributeValue = new AttributeValue();
                    attributeValue.setValue(attributeValueCreateDto.getValue());
                    attributeValue.setAttribute(attributeIdLabelMap.get(attributeValueCreateDto.getAttributeId()));
                    return attributeValue;
                })
                .toList();
    }

    private Vendor getVendorForUpdate(ProductUpdateDto productUpdateDto) {
        Long vendorId = productUpdateDto.getVendorId();
        return Objects.isNull(vendorId) ?
                null :
                vendorRepository.findById(vendorId).orElseThrow(() -> new VendorNotFoundException(vendorId));
    }

    private Subcategory getSubcategoryForUpdate(ProductUpdateDto productUpdateDto) {
        Long subcategoryId = productUpdateDto.getSubcategoryId();
        return Objects.isNull(subcategoryId) ? null :
                subcategoryRepository.findById(subcategoryId)
                        .orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
    }

    private Product updateProductImages(Product productForImagesUpdate, ProductUpdateDto productUpdateDto) {
        List<ImageUpdateDto> imageUpdateDtos = productUpdateDto.getImageUpdateDtos();
        if (Objects.isNull(imageUpdateDtos)) return productForImagesUpdate;
        Map<Long, Image> productImageIdImageMap = productForImagesUpdate.getImages().stream()
                .collect(Collectors.toMap(Image::getId, Function.identity()));
        imageUpdateDtos.forEach(imageUpdateDto -> {
            Long imageId = imageUpdateDto.getId();
            if (Objects.isNull(imageId)) {
                productForImagesUpdate.addImage(imageMapper.toImage(imageUpdateDto));
                return;
            }
            Image image = productImageIdImageMap.get(imageId);
            if (Objects.isNull(image)) throw new ImageNotFoundException(imageId);
            productForImagesUpdate.removeImage(image);
            if (!Objects.isNull(imageUpdateDto.getFilePath())) {
                image.setFilePath(imageUpdateDto.getFilePath());
                productForImagesUpdate.addImage(image);
            }
        });
        return productForImagesUpdate;
    }

    private Product updateAttributeValues(Product productForAttributesUpdate, ProductUpdateDto productUpdateDto) {
        List<AttributeValueUpdateDto> attributeValueUpdateDtos = productUpdateDto.getAttributeValueUpdateDtos();
        if (Objects.isNull(attributeValueUpdateDtos)) return productForAttributesUpdate;
        Map<Long, AttributeValue> productAttributeIdAttributeValueMap = productForAttributesUpdate.getAttributeValues().stream()
                .collect(Collectors.toMap(attributeValue -> attributeValue.getAttribute().getId(),
                        Function.identity()));
        attributeValueUpdateDtos.forEach(attributeValueUpdateDto -> {
            Long attributeId = attributeValueUpdateDto.getAttributeId();
            AttributeValue attributeValue = productAttributeIdAttributeValueMap.get(attributeId);
            if (Objects.isNull(attributeValue)) {
                Attribute existedAttribute = attributeRepository.findById(attributeId)
                        .orElseThrow(() -> new AttributeNotFoundException(attributeId));
                AttributeValue newAttributeValue = new AttributeValue();
                newAttributeValue.setAttribute(existedAttribute);
                newAttributeValue.setValue(attributeValueUpdateDto.getValue());
                productForAttributesUpdate.addAttributeValue(newAttributeValue);
                return;
            }
            productForAttributesUpdate.removeAttributeValue(attributeValue);
            if (!Objects.isNull(attributeValueUpdateDto.getValue())) {
                attributeValue.setValue(attributeValueUpdateDto.getValue());
                productForAttributesUpdate.addAttributeValue(attributeValue);
            }
        });
        return productForAttributesUpdate;
    }
}