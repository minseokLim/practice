package com.minseoklim.toyproject2024.auth.domain.service

import com.minseoklim.toyproject2024.auth.domain.model.RefreshToken
import com.minseoklim.toyproject2024.auth.domain.repository.RefreshTokenRepository
import com.minseoklim.toyproject2024.auth.infra.JwtTokenParser
import com.minseoklim.toyproject2024.auth.infra.JwtTokenProvider
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@Import(JwtTokenProvider::class, JwtTokenParser::class, RefreshTokenValidator::class)
@ActiveProfiles("test")
class RefreshTokenValidatorTest {

    @Autowired
    private lateinit var refreshTokenRepository: RefreshTokenRepository

    @Autowired
    private lateinit var tokenProvider: TokenProvider

    @Autowired
    private lateinit var refreshTokenValidator: RefreshTokenValidator

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
        val accessTokenId = "accessTokenId"
        val authentication = TestingAuthenticationToken("member", "password")
        val accessToken = tokenProvider.createAccessToken(authentication, accessTokenId)
        refreshTokenRepository.save(RefreshToken(accessTokenId, 1, accessToken))

        // when, then
        assertThatThrownBy {
            refreshTokenValidator.validate(accessToken)
        }.isInstanceOf(BadCredentialsException::class.java)
    }
}
