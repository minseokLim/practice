package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentOutput

data class MakeCardPaymentResponse private constructor(
    val id: Int,
    val cardId: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun of(output: MakeCardPaymentOutput): MakeCardPaymentResponse {
            return with(output) {
                MakeCardPaymentResponse(
                    id = id,
                    cardId = cardId,
                    amount = amount,
                    productName = productName,
                    isCanceled = isCanceled
                )
            }
        }
    }
}
