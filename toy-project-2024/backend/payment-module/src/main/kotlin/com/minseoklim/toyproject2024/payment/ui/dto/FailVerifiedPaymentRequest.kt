package com.minseoklim.toyproject2024.payment.ui.dto

import com.minseoklim.toyproject2024.payment.application.dto.FailVerifiedPaymentInput

data class FailVerifiedPaymentRequest(
    val paymentId: Long
) {
    fun toInput(): FailVerifiedPaymentInput {
        return FailVerifiedPaymentInput(
            paymentId = paymentId
        )
    }
}
