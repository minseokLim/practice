package com.minseoklim.toyproject2024.payment.ui.dto

import com.minseoklim.toyproject2024.payment.application.dto.CheckOutVerifiedPaymentOutput
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CheckOutVerifiedPaymentResponse private constructor(
    val id: Long,
    val amount: Long,
    val productName: String,
    val paymentUid: String,
    val status: VerifiedPaymentStatus
) {
    companion object {
        fun from(output: CheckOutVerifiedPaymentOutput): CheckOutVerifiedPaymentResponse {
            return CheckOutVerifiedPaymentResponse(
                id = output.id,
                amount = output.amount,
                productName = output.productName,
                paymentUid = output.paymentUid,
                status = output.status
            )
        }
    }
}
