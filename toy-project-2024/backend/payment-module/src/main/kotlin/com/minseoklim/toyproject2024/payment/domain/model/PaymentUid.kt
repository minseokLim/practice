package com.minseoklim.toyproject2024.payment.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects
import java.util.UUID

@Embeddable
class PaymentUid {
    @Column(name = "payment_uid")
    val value: String = generatePaymentUid()

    private fun generatePaymentUid(): String {
        return UUID.randomUUID().toString()
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)
}
