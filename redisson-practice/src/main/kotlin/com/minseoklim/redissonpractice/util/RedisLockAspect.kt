package com.minseoklim.redissonpractice.util

import java.util.concurrent.TimeUnit
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class RedisLockAspect(
    private val redissonClient: RedissonClient
) {
    companion object {
        private const val LOCK_SUFFIX = ":lock"
        private const val WAIT_TIME_SECONDS = 3L
        private const val LEASE_TIME_SECONDS = 3L
    }

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(RedisLocked)")
    fun executeWithLock(joinPoint: ProceedingJoinPoint): Any? {
        val args = joinPoint.args
        val key = RedisKeyResolver.getKey(args[2], "product", "order")
        val lock = redissonClient.getLock(key + LOCK_SUFFIX)

        try {
            lock.tryLock(WAIT_TIME_SECONDS, LEASE_TIME_SECONDS, TimeUnit.SECONDS)
            return joinPoint.proceed(args)
        } finally {
            unlock(lock)
            logger.info("Redis unlocked!!!!")
        }
    }

    private fun unlock(lock: RLock?) {
        if (lock != null && lock.isLocked) {
            lock.unlock()
        }
    }
}
