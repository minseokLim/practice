package com.minseoklim.redissonpractice.domain

import org.springframework.stereotype.Component
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.minseoklim.redissonpractice.util.KeyValueStore
import com.minseoklim.redissonpractice.util.RedisKeyResolver

@Component
class StockCountProjector(
    private val keyValueStore: KeyValueStore,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) {
    fun project(productId: Long) {
        CoroutineScope(Dispatchers.Default).launch {
            projectInBackground(productId)
        }
    }

    private fun projectInBackground(productId: Long) {
        val product = productRepository.findById(productId).orElseThrow { IllegalArgumentException() }

        val key = RedisKeyResolver.getKey(productId, "product", "order")
        val stockCount = keyValueStore.getValue<Int>(key).orElseGet {
            product.totalCount - orderRepository.findAllByProductId(productId).count()
        }

        if (product.stockCount != stockCount) {
            product.applyStockCount(stockCount)
            productRepository.save(product)
        }
    }
}
