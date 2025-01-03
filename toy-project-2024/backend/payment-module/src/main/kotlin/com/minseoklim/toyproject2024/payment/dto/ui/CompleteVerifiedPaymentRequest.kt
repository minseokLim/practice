package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentInput

data class CompleteVerifiedPaymentRequest(
    val paymentId: Long
) {
    fun toInput(): CompleteVerifiedPaymentInput {
        return CompleteVerifiedPaymentInput(
            paymentId = paymentId
        )
    }
}
