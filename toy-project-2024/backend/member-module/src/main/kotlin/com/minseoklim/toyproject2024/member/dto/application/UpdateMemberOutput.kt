package com.minseoklim.toyproject2024.member.dto.application

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType

data class UpdateMemberOutput private constructor(
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
        fun of(member: Member): UpdateMemberOutput {
            return with(member) {
                UpdateMemberOutput(
                    id = checkNotNull(id),
                    loginId = loginId?.value,
                    name = TextEncryptUtil.decrypt(name.encryptedValue),
                    email = email?.let { TextEncryptUtil.decrypt(checkNotNull(email).encryptedValue) },
                    roles = getRoles(),
                    socialTypes = getSocialLinks().map { it.socialType }.toSet(),
                    isDeleted = isDeleted,
                    version = checkNotNull(version)
                )
            }
        }
    }
}
