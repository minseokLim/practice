package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.model.AccessToken
import com.minseoklim.toyproject2024.auth.domain.model.RefreshToken
import com.minseoklim.toyproject2024.auth.domain.repository.AccessTokenRepository
import com.minseoklim.toyproject2024.auth.domain.repository.RefreshTokenRepository
import com.minseoklim.toyproject2024.auth.domain.service.TokenIdGenerator
import com.minseoklim.toyproject2024.auth.domain.service.TokenProvider
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateTokenService(
    private val tokenProvider: TokenProvider,
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun createToken(authentication: Authentication): TokenResponse {
        val tokenId = TokenIdGenerator.generate()
        val accessToken = tokenProvider.createAccessToken(authentication, tokenId)
        accessTokenRepository.save(AccessToken(tokenId, authentication.name.toInt(), accessToken))

        val refreshToken = tokenProvider.createRefreshToken(tokenId)
        refreshTokenRepository.save(RefreshToken(tokenId, authentication.name.toInt(), refreshToken))

        return TokenResponse(accessToken, refreshToken)
    }
}
