package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.RefreshTokenValidator
import com.minseoklim.toyproject2024.auth.domain.TokenParser
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RefreshTokenService(
    private val refreshTokenValidator: RefreshTokenValidator,
    private val deleteTokenService: DeleteTokenService,
    private val createTokenService: CreateTokenService,
    private val tokenParser: TokenParser
) {
    fun refreshToken(request: TokenRequest): TokenResponse {
        refreshTokenValidator.validate(request.refreshToken)
        deleteTokenService.deleteToken(request.accessToken, request.refreshToken)
        val authentication = tokenParser.extractAuthentication(request.accessToken)
        return createTokenService.createToken(authentication)
    }
}
