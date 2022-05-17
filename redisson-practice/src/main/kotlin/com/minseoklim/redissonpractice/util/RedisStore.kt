package com.minseoklim.redissonpractice.util

import java.util.Optional
import java.util.concurrent.TimeUnit
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition

@Component
class RedisStore(
    private val redissonClient: RedissonClient,
    private val transactionManager: PlatformTransactionManager
) : KeyValueStore {
    companion object {
        private const val LOCK_SUFFIX = ":lock"
        private const val WAIT_TIME_SECONDS = 3L
        private const val LEASE_TIME_SECONDS = 3L
    }

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun <T> getValue(key: String): Optional<T> {
        val bucket = redissonClient.getBucket<T>(key)

        if (bucket.isExists) {
            return Optional.of(bucket.get())
        }
        return Optional.empty()
    }

    override fun <T> save(key: String, value: T) {
        redissonClient.getBucket<T>(key).set(value)
    }

    override fun <T> executeWithLock(key: String, function: () -> T): T {
        val lock = redissonClient.getLock(key + LOCK_SUFFIX)

        // transaction start
        val transaction = getTransaction()

        try {
            lock.tryLock(WAIT_TIME_SECONDS, LEASE_TIME_SECONDS, TimeUnit.SECONDS)
            return function()
        } finally {
            // transaction commit
            transactionManager.commit(transaction)
            unlock(lock)
            logger.info("Redis unlocked!!!!")
        }
    }

    private fun getTransaction(): TransactionStatus {
        val transactionDefinition = DefaultTransactionDefinition()
        transactionDefinition.propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED
        return transactionManager.getTransaction(transactionDefinition)
    }

    private fun unlock(lock: RLock?) {
        if (lock != null && lock.isLocked) {
            lock.unlock()
        }
    }
}
