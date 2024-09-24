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
        fun from(output: MakeCardPaymentOutput): MakeCardPaymentResponse {
            return MakeCardPaymentResponse(
                id = output.id,
                cardId = output.cardId,
                amount = output.amount,
                productName = output.productName,
                isCanceled = output.isCanceled
            )
        }
    }
}
