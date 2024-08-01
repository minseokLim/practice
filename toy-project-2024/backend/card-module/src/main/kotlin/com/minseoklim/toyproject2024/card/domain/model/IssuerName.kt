package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class IssuerName(
    value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    @Column(name = "issuer_name")
    val value: String = value

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "카드 발급사 이름은 공백일 수 없습니다."
    }
}
