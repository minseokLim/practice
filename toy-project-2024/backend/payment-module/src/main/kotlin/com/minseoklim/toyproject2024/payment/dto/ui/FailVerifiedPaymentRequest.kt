package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.FailVerifiedPaymentInput
import jakarta.validation.constraints.NotBlank

data class FailVerifiedPaymentRequest(
    @get:NotBlank
    val paymentUid: String
) {
    fun toInput(): FailVerifiedPaymentInput {
        return FailVerifiedPaymentInput(
            paymentUid = paymentUid
        )
    }
}
