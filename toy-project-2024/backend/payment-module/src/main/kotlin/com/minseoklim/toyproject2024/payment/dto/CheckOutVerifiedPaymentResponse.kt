package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CheckOutVerifiedPaymentResponse private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val paymentUid: String,
    val status: VerifiedPaymentStatus
) {
    companion object {
        fun of(verifiedPayment: VerifiedPayment): CheckOutVerifiedPaymentResponse {
            return with(verifiedPayment) {
                CheckOutVerifiedPaymentResponse(
                    id = id!!,
                    amount = amount.value.toLong(),
                    productName = productName.value,
                    paymentUid = paymentUid.value,
                    status = status
                )
            }
        }
    }
}
