package com.minseoklim.toyproject2024.auth.infra

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.TestingAuthenticationToken

class JwtTokenProviderTest {
    private val tokenProvider =
        JwtTokenProvider(SECRET_KEY, ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS, REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS)

    @Test
    fun createAccessToken() {
        // given
        val authentication = TestingAuthenticationToken("member", "password")

        // when
        val accessToken = tokenProvider.createAccessToken(authentication, "accessTokenId")

        // then
        assertThat(accessToken).isNotNull
    }

    @Test
    fun createRefreshToken() {
        // when
        val refreshToken = tokenProvider.createRefreshToken("refreshTokenId")

        // then
        assertThat(refreshToken).isNotNull
    }

    companion object {
        private const val SECRET_KEY = "01234567890123456789012345678901"
        private const val ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS = 3600000L
        private const val REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS = 86400000L
    }
}
