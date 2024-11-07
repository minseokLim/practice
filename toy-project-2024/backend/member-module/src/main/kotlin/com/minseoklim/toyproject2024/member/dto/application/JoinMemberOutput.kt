package com.minseoklim.toyproject2024.member.dto.application

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType

data class JoinMemberOutput private constructor(
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
        fun from(member: Member): JoinMemberOutput {
            return JoinMemberOutput(
                id = checkNotNull(member.id),
                loginId = checkNotNull(member.loginId).value,
                name = TextEncryptUtil.decrypt(member.name.encryptedValue),
                email = TextEncryptUtil.decrypt(checkNotNull(member.email).encryptedValue),
                roles = member.getRoles(),
                socialTypes = member.getSocialLinks().map { it.socialType }.toSet(),
                isDeleted = member.isDeleted,
                version = checkNotNull(member.version)
            )
        }
    }
}
