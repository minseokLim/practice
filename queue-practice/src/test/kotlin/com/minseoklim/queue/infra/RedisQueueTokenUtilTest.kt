package com.minseoklim.queue.infra

import com.minseoklim.queue.exception.BadRequestException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class RedisQueueTokenUtilTest {

    @Autowired
    private lateinit var redisQueueTokenUtil: RedisQueueTokenUtil

    @BeforeEach
    fun setUp() {
        redisQueueTokenUtil.initialize()
    }

    @Test
    fun createToken() {
        // when
        val token = redisQueueTokenUtil.createToken()

        // then
        assertThat(token).isNotBlank()
    }

    @Test
    fun rankToken() {
        // given
        redisQueueTokenUtil.createToken()
        val secondToken = redisQueueTokenUtil.createToken()

        // when
        val rank = redisQueueTokenUtil.rankToken(secondToken)

        // then
        assertThat(rank).isEqualTo(1L)
    }

    @Test
    fun deleteToken() {
        // given
        val firstToken = redisQueueTokenUtil.createToken()
        val secondToken = redisQueueTokenUtil.createToken()

        // when
        redisQueueTokenUtil.deleteToken(firstToken)

        // then
        assertThat(redisQueueTokenUtil.rankToken(secondToken)).isEqualTo(0L)
    }

    @Test
    fun deleteTokenBefore() {
        // given
        val token = redisQueueTokenUtil.createToken()

        // when
        redisQueueTokenUtil.deleteTokenBefore(LocalDateTime.now())

        // then
        assertThatException().isThrownBy {
            redisQueueTokenUtil.rankToken(token)
        }.isInstanceOf(BadRequestException::class.java)
    }

    @Test
    fun initialize() {
        // given
        val token = redisQueueTokenUtil.createToken()

        // when
        redisQueueTokenUtil.initialize()

        // then
        assertThatException().isThrownBy {
            redisQueueTokenUtil.rankToken(token)
        }.isInstanceOf(BadRequestException::class.java)
    }
}
