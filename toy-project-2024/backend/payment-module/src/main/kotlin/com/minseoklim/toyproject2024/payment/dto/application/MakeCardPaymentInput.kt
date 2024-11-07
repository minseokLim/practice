package com.minseoklim.toyproject2024.payment.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.CardPayment

data class MakeCardPaymentInput(
    val cardId: Long,
    val amount: Long,
    val productName: String
) {
    fun toEntity(memberId: Long): CardPayment {
        return CardPayment(
            amount = amount,
            productName = productName,
            memberId = memberId,
            cardId = cardId
        )
    }
}
