package com.minseoklim.toyproject2024.auth.domain

import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshToken, String> {
    fun findAllByMemberId(memberId: Int): List<RefreshToken>
}
