package com.intexsoft.webshop.productqueryservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Product {
    Long productId;
    String productName;
    Vendor vendor;
    Subcategory subcategory;
    List<Image> images;
    List<AttributeValue> attributeValues;

    @Getter
    @Setter
    @FieldNameConstants
    public static class Vendor {
        Long vendorId;
        String vendorName;
        String about;
    }

    @Getter
    @Setter
    @FieldNameConstants
    public static class Subcategory {
        Long subcategoryId;
        String subcategoryName;
        String description;
        Category category;
        List<Attribute> attributes;

        @Getter
        @Setter
        @FieldNameConstants
        public static class Category {
            Long categoryId;
            String categoryName;
        }

        @Getter
        @Setter
        @FieldNameConstants
        public static class Attribute {
            Long attributeId;
            String label;
        }
    }

    @Getter
    @Setter
    @FieldNameConstants
    public static class Image {
        Long imageId;
        String filePath;
    }

    @Getter
    @Setter
    @FieldNameConstants
    public static class AttributeValue {
        Long attributeValueId;
        String value;
        Long attributeId;
    }
}