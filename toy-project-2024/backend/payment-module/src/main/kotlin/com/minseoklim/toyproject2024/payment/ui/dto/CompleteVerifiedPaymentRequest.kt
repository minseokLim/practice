package com.minseoklim.toyproject2024.payment.ui.dto

import com.minseoklim.toyproject2024.payment.application.dto.CompleteVerifiedPaymentInput

data class CompleteVerifiedPaymentRequest(
    val paymentId: Long
) {
    fun toInput(): CompleteVerifiedPaymentInput {
        return CompleteVerifiedPaymentInput(
            paymentId = paymentId
        )
    }
}
