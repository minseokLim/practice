package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.FailVerifiedPaymentInput

data class FailVerifiedPaymentRequest(
    val paymentId: Long
) {
    fun toInput(): FailVerifiedPaymentInput {
        return FailVerifiedPaymentInput(
            paymentId = paymentId
        )
    }
}
