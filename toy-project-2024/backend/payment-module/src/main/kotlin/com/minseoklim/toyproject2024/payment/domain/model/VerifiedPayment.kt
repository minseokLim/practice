package com.minseoklim.toyproject2024.payment.domain.model

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Entity
@DiscriminatorValue("VERIFIED")
class VerifiedPayment(
    amount: Long,
    productName: String,
    memberId: Long
) : Payment(amount, productName, memberId) {
    @Enumerated(EnumType.STRING)
    var status: VerifiedPaymentStatus = VerifiedPaymentStatus.CREATED
        protected set

    fun fail() {
        status = VerifiedPaymentStatus.FAILED
    }

    fun complete() {
        status = VerifiedPaymentStatus.COMPLETED
    }

    fun tamper() {
        status = VerifiedPaymentStatus.TAMPERED
    }
}
