package com.minseoklim.queue.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RedisAccessLimitDeciderTest {

    @Autowired
    private lateinit var redisAccessLimitDecider: RedisAccessLimitDecider

    @Test
    fun setAndGetAccessLimit() {
        // given
        val accessLimit = 100000L

        // when
        redisAccessLimitDecider.setAccessLimit(accessLimit)
        val result = redisAccessLimitDecider.getAccessLimit()

        // then
        assertThat(result).isEqualTo(accessLimit)
    }
}
