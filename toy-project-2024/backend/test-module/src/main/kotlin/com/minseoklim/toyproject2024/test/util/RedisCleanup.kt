package com.minseoklim.toyproject2024.test.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.test.context.ActiveProfiles

@Service
@ActiveProfiles("test")
class RedisCleanup(
    private val redisTemplate: RedisTemplate<Any, Any>
) {
    fun execute() {
        redisTemplate.keys("*").forEach {
            redisTemplate.delete(it)
        }
    }
}
