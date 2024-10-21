package com.minseoklim.toyproject2024.notification.domain.repository

import com.minseoklim.toyproject2024.notification.domain.model.PushToken
import org.springframework.data.jpa.repository.JpaRepository

interface PushTokenRepository : JpaRepository<PushToken, Int> {
    fun findByToken(token: String): PushToken?

    fun findByMemberId(memberId: Int): PushToken?
}
