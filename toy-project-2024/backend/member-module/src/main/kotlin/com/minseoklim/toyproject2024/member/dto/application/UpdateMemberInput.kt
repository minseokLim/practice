package com.minseoklim.toyproject2024.member.dto.application

import com.minseoklim.toyproject2024.member.domain.model.Member

data class UpdateMemberInput(
    val password: String?,
    val name: String,
    val email: String,
    val version: Long
) {
    fun toEntity(original: Member): Member {
        return Member(original.loginId?.value, password, name, email)
    }
}
