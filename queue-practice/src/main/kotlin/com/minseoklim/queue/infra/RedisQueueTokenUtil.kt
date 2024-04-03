package com.minseoklim.queue.infra

import com.minseoklim.queue.domain.QueueTokenGenerator
import com.minseoklim.queue.domain.QueueTokenUtil
import com.minseoklim.queue.exception.BadRequestException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class RedisQueueTokenUtil(
    private val redisTemplate: RedisTemplate<Any, Any>
) : QueueTokenUtil {
    override fun createToken(): String {
        val token = QueueTokenGenerator.generate()
        val currentTimeMillis = System.currentTimeMillis()
        redisTemplate.opsForZSet().add(QUEUE_TOKEN_KEY, token, currentTimeMillis.toDouble())
        return token
    }

    override fun rankToken(token: String): Long {
        return redisTemplate.opsForZSet().rank(QUEUE_TOKEN_KEY, token)
            ?: throw BadRequestException("INVALID_TOKEN", "유효하지 않은 토큰입니다")
    }

    override fun deleteToken(token: String) {
        redisTemplate.opsForZSet().remove(QUEUE_TOKEN_KEY, token)
    }

    override fun deleteTokenBefore(time: LocalDateTime) {
        val timeMillis = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        redisTemplate.opsForZSet().removeRangeByScore(QUEUE_TOKEN_KEY, 0.0, timeMillis.toDouble())
    }

    override fun initialize() {
        redisTemplate.delete(QUEUE_TOKEN_KEY)
    }

    companion object {
        private const val QUEUE_TOKEN_KEY = "queue_token"
    }
}
