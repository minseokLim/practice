package com.minseoklim.toyproject2024.auth.domain.service

import com.minseoklim.toyproject2024.auth.domain.repository.TokenRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Component

@Component
class RefreshTokenValidator(
    private val tokenRepository: TokenRepository,
    private val tokenParser: TokenParser
) {
    fun validate(refreshToken: String) {
        val refreshTokenId = tokenParser.extractId(refreshToken)
        if (!tokenRepository.existsById(refreshTokenId) || !tokenParser.isValidRefreshToken(refreshToken)) {
            throw BadCredentialsException("Invalid refresh token")
        }
    }
}
