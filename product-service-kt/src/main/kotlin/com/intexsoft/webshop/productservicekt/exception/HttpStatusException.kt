package com.intexsoft.webshop.productservicekt.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

abstract class HttpStatusException(
    var exceptionMessage: String,
    var status: HttpStatus,
) : RuntimeException(exceptionMessage){
    var timeStamp: LocalDateTime = LocalDateTime.now()
}