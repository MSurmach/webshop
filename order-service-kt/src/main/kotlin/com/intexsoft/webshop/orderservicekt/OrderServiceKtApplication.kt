package com.intexsoft.webshop.orderservicekt

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

val log = KotlinLogging.logger {}
@SpringBootApplication
class OrderServiceKtApplication

fun main(args: Array<String>) {
    runApplication<com.intexsoft.webshop.orderservicekt.OrderServiceKtApplication>(*args)
}