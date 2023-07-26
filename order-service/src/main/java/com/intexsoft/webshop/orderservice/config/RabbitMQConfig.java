package com.intexsoft.webshop.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rmq.event.order.exchange}")
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
}