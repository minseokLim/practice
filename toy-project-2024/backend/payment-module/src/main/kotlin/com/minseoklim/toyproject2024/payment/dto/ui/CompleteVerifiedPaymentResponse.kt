package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus
import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentOutput

data class CompleteVerifiedPaymentResponse(
    val status: VerifiedPaymentStatus
) {
    companion object {
        fun of(output: CompleteVerifiedPaymentOutput): CompleteVerifiedPaymentResponse {
            return with(output) {
                CompleteVerifiedPaymentResponse(
                    status = status
                )
            }
        }
    }
}
