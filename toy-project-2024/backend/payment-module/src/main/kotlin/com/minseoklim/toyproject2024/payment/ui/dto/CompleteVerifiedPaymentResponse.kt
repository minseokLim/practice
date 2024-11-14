package com.minseoklim.toyproject2024.payment.ui.dto

import com.minseoklim.toyproject2024.payment.application.dto.CompleteVerifiedPaymentOutput
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

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
