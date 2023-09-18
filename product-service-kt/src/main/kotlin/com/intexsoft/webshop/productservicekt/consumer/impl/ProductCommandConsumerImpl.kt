package com.intexsoft.webshop.productservicekt.consumer.impl

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.ChangeProductOrderQuantityCommand
import com.intexsoft.webshop.productservicekt.consumer.ProductCommandConsumer
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.service.ProductCommandProcessor
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.rabbit.annotation.*
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service

@Service
@RabbitListener(
    bindings = [QueueBinding(
        value = Queue(name = "\${rmq.command.queue}"),
        exchange = Exchange(name = "\${rmq.command.exchange}", type = ExchangeTypes.TOPIC),
        key = ["\${rmq.command.routing}"]
    )]
)
class ProductCommandConsumerImpl(
    private val productCommandProcessor: ProductCommandProcessor
) : ProductCommandConsumer {
    @RabbitHandler
    override fun receiveChangeProductOrderQuantityCommand(@Payload changeProductOrderQuantityCommand: ChangeProductOrderQuantityCommand) {
        log.info(
            "New event message ${changeProductOrderQuantityCommand::class.simpleName} received." +
                    " Message payload = $changeProductOrderQuantityCommand"
        )
        productCommandProcessor.processChangeProductOrderQuantityCommand(changeProductOrderQuantityCommand)
    }
}