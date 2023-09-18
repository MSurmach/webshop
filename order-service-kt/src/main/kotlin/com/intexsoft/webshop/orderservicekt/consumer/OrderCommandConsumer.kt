package com.intexsoft.webshop.orderservicekt.consumer

import com.intexsoft.webshop.messagecommon.command.orderorchestrator.FailOrderCommand

interface OrderCommandConsumer {
    fun receiveFailOrderCommand(failOrderCommand: FailOrderCommand)
}