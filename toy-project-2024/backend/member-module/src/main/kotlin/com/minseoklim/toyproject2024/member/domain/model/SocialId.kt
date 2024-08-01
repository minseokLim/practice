package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class SocialId(
    @Column(name = "social_id")
    val value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "소셜 ID는 공백일 수 없습니다."
    }
}
