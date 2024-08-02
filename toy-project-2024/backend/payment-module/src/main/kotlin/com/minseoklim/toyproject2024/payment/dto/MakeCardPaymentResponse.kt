package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.CardPayment

data class MakeCardPaymentResponse(
    val id: Int,
    val cardId: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun of(cardPayment: CardPayment): MakeCardPaymentResponse {
            return with(cardPayment) {
                MakeCardPaymentResponse(
                    id = id!!,
                    cardId = cardId,
                    amount = amount.value.toLong(),
                    productName = productName.value,
                    isCanceled = isCanceled
                )
            }
        }
    }
}
