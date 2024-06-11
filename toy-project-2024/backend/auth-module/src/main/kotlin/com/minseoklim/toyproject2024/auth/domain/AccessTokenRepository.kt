package com.minseoklim.toyproject2024.auth.domain

import org.springframework.data.jpa.repository.JpaRepository

interface AccessTokenRepository : JpaRepository<AccessToken, String> {
    fun findAllByMemberId(memberId: Int): List<AccessToken>
}
