package com.minseoklim.toyproject2024.auth.domain.repository

import com.minseoklim.toyproject2024.auth.domain.model.Token
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<Token, String> {
    fun findAllByMemberId(memberId: Long): List<Token>
}
