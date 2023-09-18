package com.intexsoft.webshop.productservicekt

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

val log = KotlinLogging.logger {}
@SpringBootApplication
class ProductServiceKtApplication

fun main(args: Array<String>) {
    runApplication<ProductServiceKtApplication>(*args)
}