package com.minseoklim.toyproject2024.payment.domain.model

import com.minseoklim.toyproject2024.common.util.CommonUtil.entityEmbeddableEquals
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class ProductName(
    value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    @Column(name = "product_name")
    val value: String = value

    final override fun equals(other: Any?): Boolean {
        return this.entityEmbeddableEquals(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "상품명은 공백일 수 없습니다."
    }
}
