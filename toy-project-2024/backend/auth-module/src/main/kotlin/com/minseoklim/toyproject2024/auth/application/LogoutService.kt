package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.service.AccessTokenDbCheckFlagActivator
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import com.minseoklim.toyproject2024.auth.dto.LogoutRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutService(
    private val deleteTokenService: DeleteTokenService,
    private val accessTokenDbCheckFlagActivator: AccessTokenDbCheckFlagActivator,
    private val tokenParser: TokenParser,
) {
    fun logout(request: LogoutRequest) {
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
