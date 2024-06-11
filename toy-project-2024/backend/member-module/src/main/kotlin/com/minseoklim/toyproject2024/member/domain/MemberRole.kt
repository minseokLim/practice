package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class MemberRole(
    @Enumerated(EnumType.STRING)
    val role: Role
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberRole

        return role == other.role
    }

    override fun hashCode(): Int {
        return role.hashCode()
    }
}
