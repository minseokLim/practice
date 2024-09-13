package com.minseoklim.toyproject2024.member.dto.ui

import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class QueryMemberResponse private constructor(
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
        fun of(output: QueryMemberOutput): QueryMemberResponse {
            return QueryMemberResponse(
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
