package com.minseoklim.toyproject2024.member.dto

import com.minseoklim.toyproject2024.auth.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.SocialType

data class MemberResponse(
    val id: Int,
    val loginId: String?,
    val name: String,
    val email: String?,
    val roles: Set<Role>,
    val socialTypes: Set<SocialType>,
    val isDeleted: Boolean
) {
    companion object {
        fun of(member: Member): MemberResponse {
            return with(member) {
                MemberResponse(
                    id = id!!,
                    loginId = loginId?.value,
                    name = name.value,
                    email = email?.value,
                    roles = getRoles(),
                    socialTypes = getSocialLinks().map { it.socialType }.toSet(),
                    isDeleted = isDeleted
                )
            }
        }
    }
}
