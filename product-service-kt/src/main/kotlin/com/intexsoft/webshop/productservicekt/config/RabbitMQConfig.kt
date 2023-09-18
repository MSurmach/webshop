package com.intexsoft.webshop.productservicekt.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Value("\${rmq.event.product.exchange}")
    private lateinit var eventExchangeName: String

    @Value("\${rmq.command.queue}")
    private lateinit var commandQueueName: String

    @Value("\${rmq.command.exchange}")
    private lateinit var commandExchangeName: String

    @Value("\${rmq.command.routing}")
    private lateinit var commandRouting: String

    @Bean
    fun eventExchange(): TopicExchange = TopicExchange(eventExchangeName)

    @Bean
    fun commandQueue(): Queue = Queue(commandQueueName)

    @Bean
    fun commandExchange(): TopicExchange = TopicExchange(commandExchangeName)

    @Bean
    fun commandBinding(): Binding = BindingBuilder.bind(commandQueue()).to(commandExchange()).with(commandRouting)

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        jackson2JsonMessageConverter: Jackson2JsonMessageConverter
    ): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jackson2JsonMessageConverter
        return rabbitTemplate
    }
}