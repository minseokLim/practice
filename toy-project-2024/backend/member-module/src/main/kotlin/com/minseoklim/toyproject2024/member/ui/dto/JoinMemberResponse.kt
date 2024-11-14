package com.minseoklim.toyproject2024.member.ui.dto

import com.minseoklim.toyproject2024.member.application.dto.JoinMemberOutput
import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType

data class JoinMemberResponse private constructor(
    val id: Long,
    val loginId: String,
    val name: String,
    val email: String,
    val roles: Set<Role>,
    val socialTypes: Set<SocialType>,
    val isDeleted: Boolean,
    val version: Long
) {
    companion object {
        fun from(output: JoinMemberOutput): JoinMemberResponse {
            return JoinMemberResponse(
                id = output.id,
                loginId = output.loginId,
                name = output.name,
                email = output.email,
                roles = output.roles,
                socialTypes = output.socialTypes,
                isDeleted = output.isDeleted,
                version = output.version
            )
        }
    }
}
