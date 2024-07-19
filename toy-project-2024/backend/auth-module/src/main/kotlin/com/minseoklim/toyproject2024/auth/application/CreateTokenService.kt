package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.model.Token
import com.minseoklim.toyproject2024.auth.domain.repository.TokenRepository
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
    private val tokenRepository: TokenRepository
) {
    fun createToken(authentication: Authentication): TokenResponse {
        val tokenId = TokenIdGenerator.generate()
        val accessToken = tokenProvider.createAccessToken(authentication, tokenId)
        val refreshToken = tokenProvider.createRefreshToken(tokenId)
        tokenRepository.save(Token(tokenId, authentication.name.toInt(), accessToken, refreshToken))

        return TokenResponse(accessToken, refreshToken)
    }
}
