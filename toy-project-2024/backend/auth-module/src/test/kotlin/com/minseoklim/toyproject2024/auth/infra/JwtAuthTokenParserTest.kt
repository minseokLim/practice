package com.minseoklim.toyproject2024.auth.infra

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.TestingAuthenticationToken

class JwtAuthTokenParserTest {
    private val tokenParser = JwtAuthTokenParser(SECRET_KEY)
    private val tokenProvider =
        JwtAuthTokenProvider(SECRET_KEY, ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS, REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS)

    @Test
    fun extractAuthentication() {
        // given
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken1 = tokenProvider.createAccessToken(authentication, "accessTokenId")

        // when
        val extractedAuthentication1 = tokenParser.extractAuthentication(accessToken1)

        // then
        assertThat(extractedAuthentication1).isNotNull

        // given
        val shortTermTokenProvider = JwtAuthTokenProvider(SECRET_KEY, 1, 1)
        val accessToken2 = shortTermTokenProvider.createAccessToken(authentication, "accessTokenId")

        // when
        val extractedAuthentication2 = tokenParser.extractAuthentication(accessToken2)

        // then
        assertThat(extractedAuthentication2).isNotNull

        // when, then
        assertThatThrownBy {
            tokenParser.extractAuthentication("invalidToken")
        }.isInstanceOf(BadCredentialsException::class.java)
    }

    @Test
    fun extractId() {
        // given
        val id = "accessTokenId"
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken1 = tokenProvider.createAccessToken(authentication, id)

        // when
        val extractedId1 = tokenParser.extractId(accessToken1)

        // then
        assertThat(extractedId1).isEqualTo(id)

        // given
        val shortTermTokenProvider = JwtAuthTokenProvider(SECRET_KEY, 1, 1)
        val accessToken2 = shortTermTokenProvider.createAccessToken(authentication, id)

        // when
        val extractedId2 = tokenParser.extractId(accessToken2)

        // then
        assertThat(extractedId2).isEqualTo(id)

        // when, then
        assertThatThrownBy {
            tokenParser.extractId("invalidToken")
        }.isInstanceOf(BadCredentialsException::class.java)
    }

    @Test
    fun isValidAccessToken() {
        // given
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken = tokenProvider.createAccessToken(authentication, "accessTokenId")

        // when
        val validated1 = tokenParser.isValidAccessToken(accessToken)

        // then
        assertThat(validated1).isTrue

        // given
        val shortTermTokenProvider = JwtAuthTokenProvider(SECRET_KEY, 1, 1)
        val shortTermAccessToken = shortTermTokenProvider.createAccessToken(authentication, "accessTokenId")

        // when
        val validated2 = tokenParser.isValidAccessToken(shortTermAccessToken)

        // then
        assertThat(validated2).isFalse

        // when
        val validated3 = tokenParser.isValidAccessToken("invalidToken")

        // then
        assertThat(validated3).isFalse

        // given
        val refreshToken = tokenProvider.createRefreshToken("refreshTokenId")

        // when
        val validated4 = tokenParser.isValidAccessToken(refreshToken)

        // then
        assertThat(validated4).isFalse
    }

    @Test
    fun isValidRefreshToken() {
        // given
        val refreshToken = tokenProvider.createRefreshToken("refreshTokenId")

        // when
        val validated1 = tokenParser.isValidRefreshToken(refreshToken)

        // then
        assertThat(validated1).isTrue

        // given
        val shortTermTokenProvider = JwtAuthTokenProvider(SECRET_KEY, 1, 1)
        val shortTermRefreshToken = shortTermTokenProvider.createRefreshToken("refreshTokenId")

        // when
        val validated2 = tokenParser.isValidRefreshToken(shortTermRefreshToken)

        // then
        assertThat(validated2).isFalse

        // when
        val validated3 = tokenParser.isValidRefreshToken("invalidToken")

        // then
        assertThat(validated3).isFalse

        // given
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken = tokenProvider.createAccessToken(authentication, "accessTokenId")

        // when
        val validated4 = tokenParser.isValidRefreshToken(accessToken)

        // then
        assertThat(validated4).isFalse
    }

    companion object {
        private const val SECRET_KEY = "01234567890123456789012345678901"
        private const val ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS = 3600000L
        private const val REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS = 86400000L
    }
}
