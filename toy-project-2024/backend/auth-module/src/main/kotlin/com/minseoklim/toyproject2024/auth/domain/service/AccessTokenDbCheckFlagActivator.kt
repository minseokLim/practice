package com.minseoklim.toyproject2024.auth.domain.service

import com.minseoklim.toyproject2024.auth.domain.model.AccessTokenDbCheckFlag
import com.minseoklim.toyproject2024.auth.domain.repository.AccessTokenDbCheckFlagRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AccessTokenDbCheckFlagActivator(
    private val accessTokenDbCheckFlagRepository: AccessTokenDbCheckFlagRepository,
    @Value("\${auth.access-token-db-check-in-milliseconds}")
    private val accessTokenDbCheckInMilliseconds: Long
) {
    fun activateAccessTokenDbCheckFlag(memberId: Long) {
        accessTokenDbCheckFlagRepository.save(
            AccessTokenDbCheckFlag(
                memberId = memberId,
                timeToLive = accessTokenDbCheckInMilliseconds
            )
        )
    }
}
