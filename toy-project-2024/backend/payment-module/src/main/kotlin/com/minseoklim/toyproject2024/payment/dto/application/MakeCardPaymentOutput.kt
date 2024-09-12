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
        fun of(cardPayment: CardPayment): MakeCardPaymentOutput {
            return with(cardPayment) {
                MakeCardPaymentOutput(
                    id = checkNotNull(id),
                    cardId = cardId,
                    amount = amount.value.toLong(),
                    productName = productName.value,
                    isCanceled = isCanceled
                )
            }
        }
    }
}
