package com.minseoklim.toyproject2024.payment.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.CardPayment

data class MakeCardPaymentOutput private constructor(
    val id: Int,
    val cardId: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun from(cardPayment: CardPayment): MakeCardPaymentOutput {
            return MakeCardPaymentOutput(
                id = checkNotNull(cardPayment.id),
                cardId = cardPayment.cardId,
                amount = cardPayment.amount.value.toLong(),
                productName = cardPayment.productName.value,
                isCanceled = cardPayment.isCanceled
            )
        }
    }
}
