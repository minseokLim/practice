package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.service.RefreshTokenValidator
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import com.minseoklim.toyproject2024.auth.dto.application.RefreshTokenInput
import com.minseoklim.toyproject2024.auth.dto.application.RefreshTokenOutput
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
    fun refreshToken(input: RefreshTokenInput): RefreshTokenOutput {
        refreshTokenValidator.validate(input.refreshToken)
        deleteTokenService.deleteToken(input.accessToken, input.refreshToken)
        val authentication = tokenParser.extractAuthentication(input.accessToken)
        val token = createTokenService.createToken(authentication)
        return RefreshTokenOutput(token.accessToken, token.refreshToken)
    }
}
