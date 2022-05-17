package com.minseoklim.redissonpractice.application

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.minseoklim.redissonpractice.domain.Product
import com.minseoklim.redissonpractice.domain.ProductRepository
import com.minseoklim.redissonpractice.util.KeyValueStore

@Service
@Transactional
class TestService(
    private val keyValueStore: KeyValueStore,
    private val productRepository: ProductRepository
) {
    fun test() {
        keyValueStore.executeWithLock("test") {
            keyValueStore.save("test", "testValue")
            productRepository.save(Product("연필", 100, 1))
            throw IllegalArgumentException()
        }
    }
}
