package com.intexsoft.webshop.productqueryservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    Long id;
    String name;
    Vendor vendor;
    Subcategory subcategory;
    List<Image> images;
    List<AttributeValue> attributeValues;

    @Getter
    @Setter
    public static class Vendor {
        Long id;
        String name;
        String about;
    }

    @Getter
    @Setter
    public static class Subcategory {
        Long id;
        String name;
        String description;
        Category category;
        List<Attribute> attributes;
    }

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

    @Getter
    @Setter
    public static class Image {
        Long id;
        String filePath;
    }

    @Getter
    @Setter
    public static class AttributeValue {
        Long id;
        String value;
        Long attributeId;
    }
}