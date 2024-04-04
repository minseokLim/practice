package com.minseoklim.queue.infra

import com.minseoklim.queue.domain.AccessLimitDecider
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisAccessLimitDecider(
    private val redisTemplate: RedisTemplate<Any, Any>
) : AccessLimitDecider {
    override fun getAccessLimit(): Long {
        val accessLimit = redisTemplate.opsForValue()[ACCESS_LIMIT_KEY]

        if (accessLimit == null) {
            setAccessLimit(DEFAULT_ACCESS_LIMIT)
            return DEFAULT_ACCESS_LIMIT
        }

        return accessLimit as Long
    }

    override fun setAccessLimit(accessLimit: Long) {
        redisTemplate.opsForValue()[ACCESS_LIMIT_KEY] = accessLimit
    }

    companion object {
        private const val ACCESS_LIMIT_KEY = "access_limit"
        private const val DEFAULT_ACCESS_LIMIT = 1000L
    }
}
