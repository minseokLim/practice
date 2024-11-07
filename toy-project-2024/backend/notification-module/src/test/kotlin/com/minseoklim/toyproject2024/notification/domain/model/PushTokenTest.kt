package com.minseoklim.toyproject2024.notification.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class PushTokenTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val pushToken1 = PushToken(token = "token", memberId = 1L)
        val pushToken2 = PushToken(token = "token", memberId = 1L)
        val pushToken3 = PushToken(token = "token", memberId = 2)
        ReflectionTestUtils.setField(pushToken1, "id", 1L)
        ReflectionTestUtils.setField(pushToken2, "id", 1L)

        // when, then
        assertThat(setOf(pushToken1, pushToken2, pushToken3)).hasSize(2)
    }
}
