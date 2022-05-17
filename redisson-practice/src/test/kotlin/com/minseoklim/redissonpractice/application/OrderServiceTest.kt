package com.minseoklim.redissonpractice.application

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import com.minseoklim.redissonpractice.domain.OrderRepository
import com.minseoklim.redissonpractice.domain.Product
import com.minseoklim.redissonpractice.domain.ProductRepository

@SpringBootTest
internal class OrderServiceTest {
    @Autowired
    private lateinit var redissonClient: RedissonClient

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var orderService: OrderService

    @BeforeEach
    internal fun setUp() {
        orderRepository.deleteAll()
        productRepository.deleteAll()
        redissonClient.keys.flushall()
    }

    @Test
    fun 재고보다_많은_빵을_주문() {
        // 1000개의 빵이 준비되어 있다.
        val productId = productRepository.save(Product("포켓몬빵", 1000, 1000)).id!!

        assertThatIllegalArgumentException().isThrownBy {
            runBlocking {
                // 1000명이 2개씩 주문
                (1..1000).map {
                    async(Dispatchers.IO) {
                        orderService.order(it.toLong(), 2, productId)
                    }
                }.awaitAll()
            }
        }
    }

    @Test
    fun 재고_수량_확인() {
        // 1000개의 빵이 준비되어 있다.
        val productId = productRepository.save(Product("포켓몬빵", 1000, 1000)).id!!

        runBlocking {
            // 100명이 5개씩 주문
            (1..100).map {
                async(Dispatchers.IO) {
                    orderService.order(it.toLong(), 5, productId)
                }
            }.awaitAll()
        }

        // 백그라운드에서 RDB 동기화가 다 될 때까지 기다린다
        Thread.sleep(3000)

        val product = productRepository.findById(productId).get()
        assertThat(product.stockCount).isEqualTo(500)
    }
}
