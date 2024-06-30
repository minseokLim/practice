package com.minseoklim.toyproject2024.auth.domain.repository

import com.minseoklim.toyproject2024.auth.domain.model.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshToken, String> {
    fun findAllByMemberId(memberId: Int): List<RefreshToken>
}
