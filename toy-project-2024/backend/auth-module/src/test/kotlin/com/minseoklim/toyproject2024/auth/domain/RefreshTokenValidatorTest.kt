package com.minseoklim.toyproject2024.auth.domain

import com.minseoklim.toyproject2024.auth.infra.JwtTokenParser
import com.minseoklim.toyproject2024.auth.infra.JwtTokenProvider
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.authentication.BadCredentialsException

@DataJpaTest
class RefreshTokenValidatorTest {

    @Autowired
    private lateinit var refreshTokenRepository: RefreshTokenRepository
    private lateinit var tokenProvider: TokenProvider
    private lateinit var tokenParser: TokenParser
    private lateinit var refreshTokenValidator: RefreshTokenValidator

    @BeforeEach
    fun setUp() {
        tokenProvider =
            JwtTokenProvider(SECRET_KEY, ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS, REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS)
        tokenParser = JwtTokenParser(SECRET_KEY)
        refreshTokenValidator = RefreshTokenValidator(refreshTokenRepository, tokenParser)
    }

    @Test
    fun validate() {
        // given
        val refreshTokenId = "refreshTokenId"
        val refreshToken1 = tokenProvider.createRefreshToken(refreshTokenId)
        refreshTokenRepository.save(RefreshToken(refreshTokenId, 1, refreshToken1))

        // when, then
        assertThatNoException().isThrownBy {
            refreshTokenValidator.validate(refreshToken1)
        }

        // given
        val refreshToken2 = tokenProvider.createRefreshToken("invalidId")

        // when, then
        assertThatThrownBy {
            refreshTokenValidator.validate(refreshToken2)
        }.isInstanceOf(BadCredentialsException::class.java)

        // given
        val refreshToken3 = "invalidToken"

        // when, then
        assertThatThrownBy {
            refreshTokenValidator.validate(refreshToken3)
        }.isInstanceOf(BadCredentialsException::class.java)
    }

    companion object {
        private const val SECRET_KEY = "01234567890123456789012345678901"
        private const val ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS = 3600000L
        private const val REFRESH_TOKEN_VALIDITY_IN_MILLISECONDS = 86400000L
    }
}
