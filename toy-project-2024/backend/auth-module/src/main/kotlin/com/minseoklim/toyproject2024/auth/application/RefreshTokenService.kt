package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.service.RefreshTokenValidator
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import com.minseoklim.toyproject2024.auth.dto.RefreshTokenRequest
import com.minseoklim.toyproject2024.auth.dto.RefreshTokenResponse
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
    fun refreshToken(request: RefreshTokenRequest): RefreshTokenResponse {
        refreshTokenValidator.validate(request.refreshToken)
        deleteTokenService.deleteToken(request.accessToken, request.refreshToken)
        val authentication = tokenParser.extractAuthentication(request.accessToken)
        val token = createTokenService.createToken(authentication)
        return RefreshTokenResponse(token.accessToken, token.refreshToken)
    }
}
