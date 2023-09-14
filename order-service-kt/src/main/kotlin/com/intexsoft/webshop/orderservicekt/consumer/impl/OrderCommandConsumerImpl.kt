package com.intexsoft.webshop.orderservicekt.consumer.impl

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand
import com.intexsoft.webshop.orderservicekt.consumer.OrderCommandConsumer
import com.intexsoft.webshop.orderservicekt.log
import com.intexsoft.webshop.orderservicekt.model.enums.StatusName
import com.intexsoft.webshop.orderservicekt.service.StatusService
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
class OrderCommandConsumerImpl(
    private val statusService: StatusService
) : OrderCommandConsumer {
    @RabbitHandler
    override fun receiveFailOrderCommand(@Payload failOrderCommand: FailOrderCommand) {
        log.info("New event message ${failOrderCommand::class.simpleName} received. Message payload = $failOrderCommand")
        statusService.addStatusToOrderWithId(failOrderCommand.orderId, StatusName.FAILED)
    }
}
