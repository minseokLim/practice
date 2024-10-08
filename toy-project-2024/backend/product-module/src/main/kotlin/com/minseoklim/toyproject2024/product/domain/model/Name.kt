package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class Name(
    value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    @Column(name = "name")
    val value: String = value

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "상품명은 공백일 수 없습니다."
    }
}
