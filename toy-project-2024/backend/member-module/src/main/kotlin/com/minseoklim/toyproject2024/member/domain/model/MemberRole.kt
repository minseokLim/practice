package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.Objects

@Embeddable
class MemberRole(
    @Enumerated(EnumType.STRING)
    val role: Role
) {
    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.role == y.role }
    }

    final override fun hashCode(): Int = Objects.hash(role)
}
