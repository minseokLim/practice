package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.AccessTokenDbCheckFlagActivator
import com.minseoklim.toyproject2024.auth.domain.TokenParser
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutService(
    private val deleteTokenService: DeleteTokenService,
    private val accessTokenDbCheckFlagActivator: AccessTokenDbCheckFlagActivator,
    private val tokenParser: TokenParser,
) {
    fun logout(request: TokenRequest) {
        deleteTokenService.deleteToken(request.accessToken, request.refreshToken)
        accessTokenDbCheckFlagActivator.activateAccessTokenDbCheckFlag(extractMemberId(request.accessToken))
    }

    fun logoutAll(memberId: Int) {
        deleteTokenService.deleteAllToken(memberId)
        accessTokenDbCheckFlagActivator.activateAccessTokenDbCheckFlag(memberId)
    }

    private fun extractMemberId(accessToken: String): Int {
        return tokenParser.extractAuthentication(accessToken).name.toInt()
    }
}
