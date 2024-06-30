package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.AccessTokenDbCheckFlag
import com.minseoklim.toyproject2024.auth.domain.AccessTokenDbCheckFlagRepository
import com.minseoklim.toyproject2024.auth.domain.TokenParser
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutService(
    private val deleteTokenService: DeleteTokenService,
    private val accessTokenDbCheckFlagRepository: AccessTokenDbCheckFlagRepository,
    private val tokenParser: TokenParser,
    @Value("\${jwt.access-token-db-check-in-milliseconds}")
    private val accessTokenDbCheckInMilliseconds: Long
) {
    fun logout(request: TokenRequest) {
        deleteTokenService.deleteToken(request.accessToken, request.refreshToken)
        turnOnAccessTokenDbCheckFlag(extractMemberId(request.accessToken))
    }

    fun logoutAll(memberId: Int) {
        deleteTokenService.deleteAllToken(memberId)
        turnOnAccessTokenDbCheckFlag(memberId)
    }

    private fun turnOnAccessTokenDbCheckFlag(memberId: Int) {
        accessTokenDbCheckFlagRepository.save(
            AccessTokenDbCheckFlag(
                memberId = memberId,
                timeToLive = accessTokenDbCheckInMilliseconds
            )
        )
    }

    private fun extractMemberId(accessToken: String): Int {
        return tokenParser.extractAuthentication(accessToken).name.toInt()
    }
}
