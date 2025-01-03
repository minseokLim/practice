package com.minseoklim.toyproject2024.member.dto.application

import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.Role

data class JoinSocialMemberOutput private constructor(
    val id: Long,
    val roles: Set<Role>
) {
    companion object {
        fun from(member: Member): JoinSocialMemberOutput {
            return JoinSocialMemberOutput(
                id = checkNotNull(member.id),
                roles = member.getRoles()
            )
        }
    }
}
