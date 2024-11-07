package com.minseoklim.toyproject2024.websocket.infra

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class JwtWebSocketTokenParserTest {
    private val tokenParser = JwtWebSocketTokenParser(SECRET_KEY)
    private val tokenProvider = JwtWebSocketTokenProvider(SECRET_KEY, TOKEN_VALIDITY_IN_MILLISECONDS)

    @Test
    fun extractMemberId() {
        // given
        val memberId = 1L
        val token1 = tokenProvider.createToken(memberId)

        // when
        val extractedMemberId1 = tokenParser.extractMemberId(token1)

        // then
        assertThat(extractedMemberId1).isEqualTo(memberId.toString())

        // given
        val shortTermTokenProvider = JwtWebSocketTokenProvider(SECRET_KEY, 1)
        val token2 = shortTermTokenProvider.createToken(memberId)

        // when, then
        assertThatThrownBy {
            tokenParser.extractMemberId(token2)
        }.isInstanceOf(BadRequestException::class.java)

        // when, then
        assertThatThrownBy {
            tokenParser.extractMemberId("invalidToken")
        }.isInstanceOf(BadRequestException::class.java)
    }

    @Test
    fun isValidToken() {
        // given
        val memberId = 1L
        val token1 = tokenProvider.createToken(memberId)

        // when
        val validated1 = tokenParser.isValidToken(token1)

        // then
        assertThat(validated1).isTrue

        // given
        val shortTermTokenProvider = JwtWebSocketTokenProvider(SECRET_KEY, 1)
        val token2 = shortTermTokenProvider.createToken(memberId)

        // when
        val validated2 = tokenParser.isValidToken(token2)

        // then
        assertThat(validated2).isFalse

        // when
        val validated3 = tokenParser.isValidToken("invalidToken")

        // then
        assertThat(validated3).isFalse
    }

    companion object {
        private const val SECRET_KEY = "01234567890123456789012345678901"
        private const val TOKEN_VALIDITY_IN_MILLISECONDS = 30000L
    }
}
