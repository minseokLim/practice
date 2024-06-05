package com.minseoklim.toyproject2024.auth.infra

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.TestingAuthenticationToken

class JwtTokenParserTest {
    private val tokenParser = JwtTokenParser(SECRET_KEY)
    private val tokenProvider =
        JwtTokenProvider(SECRET_KEY, ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS, REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS)

    @Test
    fun extractAuthentication() {
        // given
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken = tokenProvider.createAccessToken(authentication)

        // when
        val extractedAuthentication = tokenParser.extractAuthentication(accessToken)

        // then
        assertThat(extractedAuthentication).isNotNull

        // when, then
        assertThatThrownBy {
            tokenParser.extractAuthentication("invalidToken")
        }.isInstanceOf(BadCredentialsException::class.java)
    }

    @Test
    fun validateAccessToken() {
        // given
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken = tokenProvider.createAccessToken(authentication)

        // when
        val validated1 = tokenParser.validateAccessToken(accessToken)

        // then
        assertThat(validated1).isTrue

        // given
        val shortTermTokenProvider = JwtTokenProvider(SECRET_KEY, 1, 1)
        val shortTermAccessToken = shortTermTokenProvider.createAccessToken(authentication)

        // when
        val validated2 = tokenParser.validateAccessToken(shortTermAccessToken)

        // then
        assertThat(validated2).isFalse

        // when
        val validated3 = tokenParser.validateAccessToken("invalidToken")

        // then
        assertThat(validated3).isFalse

        // given
        val refreshToken = tokenProvider.createRefreshToken()

        // when
        val validated4 = tokenParser.validateAccessToken(refreshToken)

        // then
        assertThat(validated4).isFalse
    }

    @Test
    fun validateRefreshToken() {
        // given
        val refreshToken = tokenProvider.createRefreshToken()

        // when
        val validated1 = tokenParser.validateRefreshToken(refreshToken)

        // then
        assertThat(validated1).isTrue

        // given
        val shortTermTokenProvider = JwtTokenProvider(SECRET_KEY, 1, 1)
        val shortTermRefreshToken = shortTermTokenProvider.createRefreshToken()

        // when
        val validated2 = tokenParser.validateRefreshToken(shortTermRefreshToken)

        // then
        assertThat(validated2).isFalse

        // when
        val validated3 = tokenParser.validateRefreshToken("invalidToken")

        // then
        assertThat(validated3).isFalse

        // given
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken = tokenProvider.createAccessToken(authentication)

        // when
        val validated4 = tokenParser.validateRefreshToken(accessToken)

        // then
        assertThat(validated4).isFalse
    }

    companion object {
        private const val SECRET_KEY = "01234567890123456789012345678901"
        private const val ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS = 3600000L
        private const val REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS = 86400000L
    }
}
