package com.intexsoft.webshop.messagecommon.event.product.impl;

import com.intexsoft.webshop.messagecommon.event.product.ProductEvent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreatedEvent extends ProductEvent {
    String name;
    Subcategory subcategory;
    Vendor vendor;
    List<Image> images;
    List<AttributeValue> attributeValues;

    @Getter
    @Setter
    public static class Subcategory {
        Long id;
        String name;
        String description;
        Category category;
        List<Attribute> attributes;

        @Getter
        @Setter
        public static class Category {
            Long id;
            String name;
        }

        @Getter
        @Setter
        public static class Attribute {
            Long id;
            String label;
        }
    }

    @Getter
    @Setter
    public static class Vendor {
        Long id;
        String name;
        String about;
    }

    @Getter
    @Setter
    public static class AttributeValue {
        Long id;
        String value;
        Long attributeId;
    }

    @Getter
    @Setter
    public static class Image {
        Long id;
        String filePath;
    }
}