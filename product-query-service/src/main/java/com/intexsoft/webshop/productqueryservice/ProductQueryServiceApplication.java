package com.intexsoft.webshop.productqueryservice;

import com.intexsoft.webshop.productqueryservice.repository.impl.ResourceRepositoryImpl;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(repositoryBaseClass = ResourceRepositoryImpl.class)
@EnableMongock
@EnableDiscoveryClient
public class ProductQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductQueryServiceApplication.class, args);
    }
}