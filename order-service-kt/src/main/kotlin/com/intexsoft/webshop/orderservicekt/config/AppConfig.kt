package com.intexsoft.webshop.orderservicekt.config;

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun jackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter(jacksonObjectMapper())
    }

    @Bean
    fun jacksonObjectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.registerModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        )
        return mapper
    }
}
