package com.minseoklim.toyproject2024.websocket.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JwtWebSocketTokenProviderTest {
    private val tokenProvider = JwtWebSocketTokenProvider(SECRET_KEY, TOKEN_VALIDITY_IN_MILLISECONDS)

    @Test
    fun createToken() {
        // given
        val memberId = 1

        // when
        val token = tokenProvider.createToken(memberId)

        // then
        assertThat(token).isNotNull
    }

    companion object {
        private const val SECRET_KEY = "01234567890123456789012345678901"
        private const val TOKEN_VALIDITY_IN_MILLISECONDS = 30000L
    }
}
