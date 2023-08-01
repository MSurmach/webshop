package com.intexsoft.webshop.productservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rmq.event.product.exchange}")
    private String eventExchangeName;
    @Value("${rmq.command.queue}")
    private String commandQueueName;
    @Value("${rmq.command.exchange}")
    private String commandExchangeName;
    @Value("${rmq.command.routing}")
    private String commandRouting;

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(eventExchangeName);
    }

    @Bean
    public Queue commandQueue() {
        return new Queue(commandQueueName);
    }

    @Bean
    public TopicExchange commandExchange() {
        return new TopicExchange(commandExchangeName);
    }

    @Bean
    public Binding commandBinding() {
        return BindingBuilder
                .bind(commandQueue())
                .to(commandExchange())
                .with(commandRouting);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2MessageConverter());
        return rabbitTemplate;
    }

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