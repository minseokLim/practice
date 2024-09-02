package com.minseoklim.toyproject2024.member.dto.ui

import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.dto.application.JoinMemberOutput

data class JoinMemberResponse private constructor(
    val id: Int,
    val loginId: String?,
    val name: String,
    val email: String?,
    val roles: Set<Role>,
    val socialTypes: Set<SocialType>,
    val isDeleted: Boolean,
    val version: Long
) {
    companion object {
        fun of(output: JoinMemberOutput): JoinMemberResponse {
            return with(output) {
                JoinMemberResponse(
                    id = id,
                    loginId = loginId,
                    name = name,
                    email = email,
                    roles = roles,
                    socialTypes = socialTypes,
                    isDeleted = isDeleted,
                    version = version
                )
            }
        }
    }
}
