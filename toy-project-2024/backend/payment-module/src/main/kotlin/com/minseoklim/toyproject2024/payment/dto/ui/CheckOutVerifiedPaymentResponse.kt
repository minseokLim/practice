package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus
import com.minseoklim.toyproject2024.payment.dto.application.CheckOutVerifiedPaymentOutput

data class CheckOutVerifiedPaymentResponse private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val paymentUid: String,
    val status: VerifiedPaymentStatus
) {
    companion object {
        fun of(output: CheckOutVerifiedPaymentOutput): CheckOutVerifiedPaymentResponse {
            return with(output) {
                CheckOutVerifiedPaymentResponse(
                    id = id,
                    amount = amount,
                    productName = productName,
                    paymentUid = paymentUid,
                    status = status
                )
            }
        }
    }
}
