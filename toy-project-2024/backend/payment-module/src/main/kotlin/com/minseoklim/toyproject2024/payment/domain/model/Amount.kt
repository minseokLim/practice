package com.minseoklim.toyproject2024.payment.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal
import java.util.Objects

@Embeddable
class Amount(
    value: Long
) {
    init {
        require(value > 0) { ERR_MSG }
    }

    @Column(name = "amount")
    val value: BigDecimal = BigDecimal.valueOf(value)

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "금액은 0보다 커야 합니다."
    }
}
