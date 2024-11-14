package com.minseoklim.toyproject2024.order.ui.dto

import com.minseoklim.toyproject2024.order.application.dto.CompleteVerifiedOrderOutput
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CompleteVerifiedOrderResponse private constructor(
    val paymentStatus: VerifiedPaymentStatus
) {
    companion object {
        fun from(output: CompleteVerifiedOrderOutput): CompleteVerifiedOrderResponse {
            return CompleteVerifiedOrderResponse(output.paymentStatus)
        }
    }
}
