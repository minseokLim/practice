package com.minseoklim.redissonpractice.application

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import com.minseoklim.redissonpractice.domain.Order
import com.minseoklim.redissonpractice.domain.OrderRepository
import com.minseoklim.redissonpractice.domain.ProductRepository
import com.minseoklim.redissonpractice.domain.StockCountProjector
import com.minseoklim.redissonpractice.util.KeyValueStore
import com.minseoklim.redissonpractice.util.RedisKeyResolver
import com.minseoklim.redissonpractice.util.RedisLocked

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val keyValueStore: KeyValueStore,
    private val stockCountProjector: StockCountProjector
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun order(userId: Long, orderCount: Int, productId: Long) {
        val product = productRepository.findById(productId).orElseThrow { IllegalArgumentException() }

        val key = RedisKeyResolver.getKey(productId, "product", "order")
        keyValueStore.executeWithLock(key) {
            val stockCount = keyValueStore.getValue<Int>(key).orElseGet {
                product.totalCount - orderRepository.findAllByProductId(productId).count()
            }

            // 주문 수량이 재고 수량보다 많은지 확인
            if (stockCount < orderCount) {
                logger.info("재고 수량 : $stockCount, 주문 수량 : $orderCount")
                throw IllegalArgumentException("주문 수량이 재고 수량보다 많습니다.")
            }

            orderRepository.save(Order(userId, orderCount, product))
            keyValueStore.save(key, stockCount - orderCount)
        }

        stockCountProjector.project(productId)
    }

    @RedisLocked
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun orderByAop(userId: Long, orderCount: Int, productId: Long) {
        val product = productRepository.findById(productId).orElseThrow { IllegalArgumentException() }

        val key = RedisKeyResolver.getKey(productId, "product", "order")
        val stockCount = keyValueStore.getValue<Int>(key).orElseGet {
            product.totalCount - orderRepository.findAllByProductId(productId).count()
        }

        // 주문 수량이 재고 수량보다 많은지 확인
        if (stockCount < orderCount) {
            logger.info("재고 수량 : $stockCount, 주문 수량 : $orderCount")
            throw IllegalArgumentException("주문 수량이 재고 수량보다 많습니다.")
        }

        orderRepository.save(Order(userId, orderCount, product))
        keyValueStore.save(key, stockCount - orderCount)

        stockCountProjector.project(productId)
    }
}
