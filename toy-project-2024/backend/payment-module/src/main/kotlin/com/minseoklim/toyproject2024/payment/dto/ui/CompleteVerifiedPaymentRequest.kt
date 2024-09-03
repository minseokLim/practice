package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentInput
import jakarta.validation.constraints.NotBlank

data class CompleteVerifiedPaymentRequest(
    @get:NotBlank
    val paymentUid: String
) {
    fun toInput(): CompleteVerifiedPaymentInput {
        return CompleteVerifiedPaymentInput(
            paymentUid = paymentUid
        )
    }
}
