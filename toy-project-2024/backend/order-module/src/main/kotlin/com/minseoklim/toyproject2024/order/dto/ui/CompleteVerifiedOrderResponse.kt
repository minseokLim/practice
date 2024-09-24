package com.minseoklim.toyproject2024.order.dto.ui

import com.minseoklim.toyproject2024.order.dto.application.CompleteVerifiedOrderOutput
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CompleteVerifiedOrderResponse(
    val paymentStatus: VerifiedPaymentStatus
) {
    companion object {
        fun from(output: CompleteVerifiedOrderOutput): CompleteVerifiedOrderResponse {
            return CompleteVerifiedOrderResponse(output.paymentStatus)
        }
    }
}
