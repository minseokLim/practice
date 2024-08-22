package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal
import java.util.Objects

@Embeddable
class Price(
    value: Long
) {
    init {
        require(value >= 0) { ERR_MSG }
    }

    @Column(name = "price")
    val value: BigDecimal = BigDecimal.valueOf(value)

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "가격은 0보다 작을 수 없습니다."
    }
}
