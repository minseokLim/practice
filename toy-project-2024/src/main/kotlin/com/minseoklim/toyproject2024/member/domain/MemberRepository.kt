package com.minseoklim.toyproject2024.member.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Int> {
    fun countByLoginIdValue(loginId: String): Int
}
