package com.intexsoft.webshop.productqueryservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intexsoft.webshop.productqueryservice.model.Product;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MappingMongoEntityInformation;

@Configuration
public class AppConfig {
    @Bean
    public Jackson2JsonMessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter(jacksonObjectMapper());
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}