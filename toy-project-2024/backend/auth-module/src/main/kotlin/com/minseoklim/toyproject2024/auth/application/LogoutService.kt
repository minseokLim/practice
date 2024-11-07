package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.service.AccessTokenDbCheckFlagActivator
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import com.minseoklim.toyproject2024.auth.dto.application.LogoutInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutService(
    private val deleteTokenService: DeleteTokenService,
    private val accessTokenDbCheckFlagActivator: AccessTokenDbCheckFlagActivator,
    private val tokenParser: TokenParser,
) {
    fun logout(input: LogoutInput) {
        deleteTokenService.deleteToken(input.accessToken, input.refreshToken)
        accessTokenDbCheckFlagActivator.activateAccessTokenDbCheckFlag(extractMemberId(input.accessToken))
    }

    fun logoutAll(memberId: Long) {
        deleteTokenService.deleteAllToken(memberId)
        accessTokenDbCheckFlagActivator.activateAccessTokenDbCheckFlag(memberId)
    }

    private fun extractMemberId(accessToken: String): Long {
        return tokenParser.extractAuthentication(accessToken).name.toLong()
    }
}
