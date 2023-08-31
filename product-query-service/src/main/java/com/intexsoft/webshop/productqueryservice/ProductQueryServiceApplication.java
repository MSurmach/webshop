package com.intexsoft.webshop.productqueryservice;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class ProductQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductQueryServiceApplication.class, args);
    }
}