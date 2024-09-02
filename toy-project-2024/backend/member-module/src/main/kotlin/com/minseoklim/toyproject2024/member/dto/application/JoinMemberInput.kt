package com.minseoklim.toyproject2024.member.dto.application

import com.minseoklim.toyproject2024.member.domain.model.Member

data class JoinMemberInput(
    val loginId: String,
    val password: String,
    val name: String,
    val email: String
) {
    fun toEntity(): Member {
        return Member(
            loginId = loginId,
            password = password,
            name = name,
            email = email
        )
    }
}
