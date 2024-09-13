package com.minseoklim.toyproject2024.member.dto.application

import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.Role

data class JoinSocialMemberOutput private constructor(
    val id: Int,
    val roles: Set<Role>
) {
    companion object {
        fun of(member: Member): JoinSocialMemberOutput {
            return JoinSocialMemberOutput(
                id = checkNotNull(member.id),
                roles = member.getRoles()
            )
        }
    }
}
