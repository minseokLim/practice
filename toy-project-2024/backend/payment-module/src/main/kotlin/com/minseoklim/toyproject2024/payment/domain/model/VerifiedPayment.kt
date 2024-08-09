package com.minseoklim.toyproject2024.payment.domain.model

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("VERIFIED")
class VerifiedPayment(
    amount: Long,
    productName: String,
    memberId: Int
) : Payment(amount, productName, memberId) {
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
