package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class StockQuantity(
    value: Int
) {
    init {
        require(value >= 0) { ERR_MSG }
    }

    @Column(name = "stock_quantity")
    val value: Int = value

    fun isSoldOut(): Boolean {
        return value == 0
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "재고 수량은 0보다 작을 수 없습니다."
    }
}
