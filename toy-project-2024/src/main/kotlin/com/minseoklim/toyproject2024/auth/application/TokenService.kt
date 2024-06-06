package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.RefreshToken
import com.minseoklim.toyproject2024.auth.domain.RefreshTokenRepository
import com.minseoklim.toyproject2024.auth.domain.TokenIdGenerator
import com.minseoklim.toyproject2024.auth.domain.TokenProvider
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TokenService(
    private val tokenProvider: TokenProvider,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun createToken(authentication: Authentication): TokenResponse {
        val accessTokenId = TokenIdGenerator.generate()
        val accessToken = tokenProvider.createAccessToken(authentication, accessTokenId)

        val refreshTokenId = TokenIdGenerator.generate()
        val refreshToken = tokenProvider.createRefreshToken(refreshTokenId)
        refreshTokenRepository.save(
            RefreshToken(
                id = refreshTokenId,
                memberId = authentication.name.toInt(),
                content = refreshToken
            )
        )

        return TokenResponse(accessToken, refreshToken)
    }
}
