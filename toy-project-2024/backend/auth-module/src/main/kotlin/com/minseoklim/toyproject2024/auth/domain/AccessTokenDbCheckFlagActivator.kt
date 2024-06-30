package com.minseoklim.toyproject2024.auth.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AccessTokenDbCheckFlagActivator(
    private val accessTokenDbCheckFlagRepository: AccessTokenDbCheckFlagRepository,
    @Value("\${jwt.access-token-db-check-in-milliseconds}")
    private val accessTokenDbCheckInMilliseconds: Long
) {
    fun activateAccessTokenDbCheckFlag(memberId: Int) {
        accessTokenDbCheckFlagRepository.save(
            AccessTokenDbCheckFlag(
                memberId = memberId,
                timeToLive = accessTokenDbCheckInMilliseconds
            )
        )
    }
}
