package com.minseoklim.toyproject2024.auth.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class LoginHistoryTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val loginHistory1 = LoginHistory(
            memberId = 1L,
            tokenId = "tokenId",
            clientIp = "clientIp",
            userAgent = "userAgent"
        )
        val loginHistory2 = LoginHistory(
            memberId = 1L,
            tokenId = "tokenId",
            clientIp = "clientIp",
            userAgent = "userAgent"
        )
        val loginHistory3 = LoginHistory(
            memberId = 1L,
            tokenId = "tokenId",
            clientIp = "clientIp",
            userAgent = "userAgent"
        )
        ReflectionTestUtils.setField(loginHistory1, "id", 1L)
        ReflectionTestUtils.setField(loginHistory2, "id", 1L)

        // when, then
        assertThat(setOf(loginHistory1, loginHistory2, loginHistory3)).hasSize(2)
    }
}
