package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class OrderProduct(
    productId: Long,
    quantity: Int
) {
    init {
        require(quantity > 0) { ERR_MSG_QUANTITY }
    }

    val productId: Long = productId
    val quantity: Int = quantity

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.productId == y.productId && x.quantity == y.quantity }
    }

    final override fun hashCode(): Int = Objects.hash(productId, quantity)

    companion object {
        const val ERR_MSG_QUANTITY = "주문 수량은 0보다 커야합니다."
    }
}
