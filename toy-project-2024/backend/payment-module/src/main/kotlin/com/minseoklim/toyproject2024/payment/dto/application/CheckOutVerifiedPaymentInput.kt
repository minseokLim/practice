package com.minseoklim.toyproject2024.payment.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment

data class CheckOutVerifiedPaymentInput(
    val amount: Long,
    val productName: String
) {
    fun toEntity(memberId: Int): VerifiedPayment {
        return VerifiedPayment(
            amount = amount,
            productName = productName,
            memberId = memberId
        )
    }
}
