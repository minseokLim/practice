package com.minseoklim.toyproject2024.auth.domain

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Component

@Component
class RefreshTokenValidator(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenParser: TokenParser
) {
    fun validate(refreshToken: String) {
        val refreshTokenId = tokenParser.extractId(refreshToken)
        if (!refreshTokenRepository.existsById(refreshTokenId) || !tokenParser.validateRefreshToken(refreshToken)) {
            throw BadCredentialsException("Invalid refresh token")
        }
    }
}
