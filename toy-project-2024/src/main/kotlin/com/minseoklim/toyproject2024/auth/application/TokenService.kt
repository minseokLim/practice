package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.RefreshToken
import com.minseoklim.toyproject2024.auth.domain.RefreshTokenRepository
import com.minseoklim.toyproject2024.auth.domain.RefreshTokenValidator
import com.minseoklim.toyproject2024.auth.domain.TokenIdGenerator
import com.minseoklim.toyproject2024.auth.domain.TokenParser
import com.minseoklim.toyproject2024.auth.domain.TokenProvider
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TokenService(
    private val tokenProvider: TokenProvider,
    private val tokenParser: TokenParser,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenValidator: RefreshTokenValidator
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

    fun refreshToken(request: TokenRequest): TokenResponse {
        refreshTokenValidator.validate(request.refreshToken)

        val refreshTokenId = tokenParser.extractId(request.refreshToken)
        refreshTokenRepository.deleteById(refreshTokenId)

        val authentication = tokenParser.extractAuthentication(request.accessToken)
        return createToken(authentication)
    }
}
