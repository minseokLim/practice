package com.minseoklim.toyproject2024.member.dto

import com.minseoklim.toyproject2024.auth.domain.Role
import com.minseoklim.toyproject2024.member.domain.Member

data class MemberResponse(
    val id: Int,
    val loginId: String,
    val name: String,
    val email: String,
    val roles: Set<Role>,
    val isDeleted: Boolean
) {
    companion object {
        fun of(member: Member): MemberResponse {
            return with(member) {
                MemberResponse(
                    id = id!!,
                    loginId = loginId.value,
                    name = name.value,
                    email = email.value,
                    roles = getRoles(),
                    isDeleted = isDeleted
                )
            }
        }
    }
}