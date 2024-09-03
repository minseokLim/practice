package com.minseoklim.toyproject2024.payment.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CheckOutVerifiedPaymentOutput private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val paymentUid: String,
    val status: VerifiedPaymentStatus
) {
    companion object {
        fun of(verifiedPayment: VerifiedPayment): CheckOutVerifiedPaymentOutput {
            return with(verifiedPayment) {
                CheckOutVerifiedPaymentOutput(
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
