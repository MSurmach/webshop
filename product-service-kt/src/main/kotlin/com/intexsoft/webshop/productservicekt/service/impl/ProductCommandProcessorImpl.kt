package com.intexsoft.webshop.productservicekt.service.impl

import com.intexsoft.webshop.messagecommon.command.ChangeProductOrderQuantityType
import com.intexsoft.webshop.messagecommon.command.orderorchestrator.ChangeProductOrderQuantityCommand
import com.intexsoft.webshop.productservicekt.log
import com.intexsoft.webshop.productservicekt.mapper.ProductEventMapper
import com.intexsoft.webshop.productservicekt.producer.ProductEventProducer
import com.intexsoft.webshop.productservicekt.repository.ProductRepository
import com.intexsoft.webshop.productservicekt.service.ProductCommandProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandProcessorImpl(
    private val productRepository: ProductRepository,
    private val productEventProducer: ProductEventProducer,
    private val productEventMapper: ProductEventMapper,
) : ProductCommandProcessor {

    @Transactional
    override fun processChangeProductOrderQuantityCommand(changeProductOrderQuantityCommand: ChangeProductOrderQuantityCommand) {
        val changeType = changeProductOrderQuantityCommand.changeType
        val productIdDeltaOrderQuantityMap = changeProductOrderQuantityCommand.productIdDeltaOrderQuantityMap
        when (changeType) {
            ChangeProductOrderQuantityType.INCREMENT -> incrementOrderQuantityForProducts(
                productIdDeltaOrderQuantityMap,
                changeProductOrderQuantityCommand.orderId
            )

            ChangeProductOrderQuantityType.DECREMENT -> decrementOrderQuantityForProducts(productIdDeltaOrderQuantityMap)
        }
    }

    private fun incrementOrderQuantityForProducts(
        productIdIncrementDeltaOrderQuantityMap: Map<Long, Short>,
        orderId: Long
    ) {
        log.info(
            "IN: trying to increment a quantity of orders for products using map of productId and increment delta." +
                    " Map size = ${productIdIncrementDeltaOrderQuantityMap.size}, content = $productIdIncrementDeltaOrderQuantityMap"
        )
        try {
            productIdIncrementDeltaOrderQuantityMap.forEach { (productId: Long, incrementDelta: Short) ->
                productRepository.updateOrderQuantityByIncrementAndProductId(
                    incrementDelta,
                    productId
                )
            }
            log.info("OUT: all products are incremented")
            productEventProducer.produceProductOrderQuantityIncremented(
                productEventMapper.toProductOrderQuantityIncrementedEvent(
                    productIdIncrementDeltaOrderQuantityMap.keys, true, orderId
                )
            )
        } catch (exception: RuntimeException) {
            log.error("Unable to increment order quantity for products. Exception message: {}", exception.message)
            productEventProducer.produceProductOrderQuantityIncremented(
                productEventMapper.toProductOrderQuantityIncrementedEvent(
                    productIdIncrementDeltaOrderQuantityMap.keys, false, orderId
                )
            )
        }
    }

    private fun decrementOrderQuantityForProducts(productIdDecrementDeltaOrderQuantityMap: Map<Long, Short>) {
        log.info(
            "IN: trying to decrement a quantity of orders for products using map of productId and decrement delta." +
                    " Map size = ${productIdDecrementDeltaOrderQuantityMap.size}, content = $productIdDecrementDeltaOrderQuantityMap"
        )
        productIdDecrementDeltaOrderQuantityMap.forEach { (productId: Long, decrementDelta: Short) ->
            productRepository.updateOrderQuantityByDecrementAndProductId(
                decrementDelta,
                productId
            )
        }
        log.info("OUT: all products are decremented")
    }
}