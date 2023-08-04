package com.intexsoft.webshop.productservice.service.impl;

import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueCreateDto;
import com.intexsoft.webshop.productservice.dto.attributevalue.AttributeValueUpdateDto;
import com.intexsoft.webshop.productservice.dto.image.ImageUpdateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductCreateDto;
import com.intexsoft.webshop.productservice.dto.product.ProductDto;
import com.intexsoft.webshop.productservice.dto.product.ProductUpdateDto;
import com.intexsoft.webshop.productservice.exception.notfound404.*;
import com.intexsoft.webshop.productservice.mapper.ImageMapper;
import com.intexsoft.webshop.productservice.mapper.ProductMapper;
import com.intexsoft.webshop.productservice.model.*;
import com.intexsoft.webshop.productservice.producer.ProductEventProducer;
import com.intexsoft.webshop.productservice.repository.AttributeRepository;
import com.intexsoft.webshop.productservice.repository.ProductRepository;
import com.intexsoft.webshop.productservice.repository.SubcategoryRepository;
import com.intexsoft.webshop.productservice.repository.VendorRepository;
import com.intexsoft.webshop.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.intexsoft.webshop.productservice.util.JsonUtils.getAsString;
import static java.util.Objects.isNull;
import static java.util.function.Function.identity;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageMapper imageMapper;
    private final ProductEventProducer productEventProducer;
    private final VendorRepository vendorRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final AttributeRepository attributeRepository;

    @Override
    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        log.info("IN: trying to save a new product. Product details = {}",
                getAsString(productCreateDto));
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
                getAsString(savedProduct));
        productEventProducer.produceProductEventCreated(productMapper.toProductEventCreated(savedProduct));
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto findProductById(Long productId) {
        log.info("IN: trying to find a product by id = {}", productId);
        Product foundProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(productId));
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
        Product existedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        Vendor vendorForUpdate = getVendorForUpdate(productUpdateDto);
        Subcategory subcategoryForUpdate = getSubcategoryForUpdate(productUpdateDto);
        Product product = productMapper.updateProduct(existedProduct, productUpdateDto, vendorForUpdate,
                subcategoryForUpdate);
        product = updateProductImages(product, productUpdateDto);
        product = updateAttributeValues(product, productUpdateDto);
        Product updatedProduct = productRepository.save(product);
        log.info("OUT: the product updated successfully. The updated product details = {}", getAsString(updatedProduct));
        if (!existedProduct.getName().equals(updatedProduct.getName()))
            productEventProducer.produceProductEventUpdated(productMapper.toProductEventUpdated(updatedProduct));
        return productMapper.toProductDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        log.info("IN: trying to delete a product by id = {}", productId);
        productRepository.deleteById(productId);
        log.info("OUT: the product with id = {} deleted successfully", productId);
        productEventProducer.produceProductEventDeleted(productMapper.toProductEventDeleted(productId));
    }

    private List<AttributeValue> createAttributeValues(ProductCreateDto productCreateDto) {
        List<AttributeValueCreateDto> attributeValueCreateDtos = productCreateDto.getAttributeValueCreateDtos();
        if (isNull(attributeValueCreateDtos)) return null;
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
        Map<Long, String> attributeIdLabelMap = attributes.stream()
                .collect(Collectors.toMap(Attribute::getId, Attribute::getLabel));
        return attributeValueCreateDtos.stream()
                .map(attributeValueCreateDto -> {
                    Attribute attribute = new Attribute();
                    attribute.setId(attributeValueCreateDto.getAttributeId());
                    attribute.setLabel(attributeIdLabelMap.get(attributeValueCreateDto.getAttributeId()));
                    AttributeValue attributeValue = new AttributeValue();
                    attributeValue.setValue(attributeValueCreateDto.getValue());
                    attributeValue.setAttribute(attribute);
                    return attributeValue;
                })
                .toList();
    }

    private Vendor getVendorForUpdate(ProductUpdateDto productUpdateDto) {
        Long vendorId = productUpdateDto.getVendorId();
        return isNull(vendorId) ?
                null :
                vendorRepository.findById(vendorId).orElseThrow(() -> new VendorNotFoundException(vendorId));
    }

    private Subcategory getSubcategoryForUpdate(ProductUpdateDto productUpdateDto) {
        Long subcategoryId = productUpdateDto.getSubcategoryId();
        return isNull(subcategoryId) ? null :
                subcategoryRepository.findById(subcategoryId)
                        .orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
    }

    private Product updateProductImages(Product productForImagesUpdate, ProductUpdateDto productUpdateDto) {
        List<ImageUpdateDto> imageUpdateDtos = productUpdateDto.getImageUpdateDtos();
        if (isNull(imageUpdateDtos)) return productForImagesUpdate;
        Map<Long, Image> product_idImage_map = productForImagesUpdate.getImages().stream()
                .collect(Collectors.toMap(Image::getId, identity()));
        imageUpdateDtos.forEach(imageUpdateDto -> {
            Long imageId = imageUpdateDto.getId();
            if (isNull(imageId)) {
                productForImagesUpdate.addImage(imageMapper.toImage(imageUpdateDto));
                return;
            }
            Image image = product_idImage_map.get(imageId);
            if (isNull(image)) throw new ImageNotFoundException(imageId);
            productForImagesUpdate.removeImage(image);
            if (!isNull(imageUpdateDto.getFilePath())) {
                image.setFilePath(imageUpdateDto.getFilePath());
                productForImagesUpdate.addImage(image);
            }
        });
        return productForImagesUpdate;
    }

    private Product updateAttributeValues(Product productForAttributesUpdate, ProductUpdateDto productUpdateDto) {
        List<AttributeValueUpdateDto> attributeValueUpdateDtos = productUpdateDto.getAttributeValueUpdateDtos();
        if (isNull(attributeValueUpdateDtos)) return productForAttributesUpdate;
        Map<Long, AttributeValue> product_attributeIdAttributeValue_map = productForAttributesUpdate.getAttributeValues().stream()
                .collect(Collectors.toMap(attributeValue -> attributeValue.getAttribute().getId(), identity()));
        attributeValueUpdateDtos.forEach(attributeValueUpdateDto -> {
            Long attributeId = attributeValueUpdateDto.getAttributeId();
            AttributeValue attributeValue = product_attributeIdAttributeValue_map.get(attributeId);
            if (isNull(attributeValue)) {
                Attribute existedAttribute = attributeRepository.findById(attributeId)
                        .orElseThrow(() -> new AttributeNotFoundException(attributeId));
                AttributeValue newAttributeValue = new AttributeValue();
                newAttributeValue.setAttribute(existedAttribute);
                newAttributeValue.setValue(attributeValueUpdateDto.getValue());
                productForAttributesUpdate.addAttributeValue(newAttributeValue);
                return;
            }
            productForAttributesUpdate.removeAttributeValue(attributeValue);
            if (!isNull(attributeValueUpdateDto.getValue())) {
                attributeValue.setValue(attributeValueUpdateDto.getValue());
                productForAttributesUpdate.addAttributeValue(attributeValue);
            }
        });
        return productForAttributesUpdate;
    }
}