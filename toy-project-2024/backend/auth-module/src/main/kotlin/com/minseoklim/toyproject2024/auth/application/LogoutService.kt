package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.AccessTokenDbCheckFlag
import com.minseoklim.toyproject2024.auth.domain.AccessTokenDbCheckFlagRepository
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LogoutService(
    private val tokenService: TokenService,
    private val accessTokenDbCheckFlagRepository: AccessTokenDbCheckFlagRepository,
    @Value("\${jwt.access-token-db-check-in-milliseconds}") private val accessTokenDbCheckInMilliseconds: Long
) {
    fun logout(request: TokenRequest) {
        tokenService.deleteAccessToken(request.accessToken)
        tokenService.deleteRefreshToken(request.refreshToken)
        turnOnAccessTokenDbCheckFlag(tokenService.extractMemberId(request.accessToken))
    }

    fun logoutAll(memberId: Int) {
        tokenService.deleteAllAccessToken(memberId)
        tokenService.deleteAllRefreshToken(memberId)
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
}
