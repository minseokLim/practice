package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus
import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentOutput

data class CompleteVerifiedPaymentResponse private constructor(
    val status: VerifiedPaymentStatus
) {
    companion object {
        fun from(output: CompleteVerifiedPaymentOutput): CompleteVerifiedPaymentResponse {
            return CompleteVerifiedPaymentResponse(
                status = output.status
            )
        }
    }
}
