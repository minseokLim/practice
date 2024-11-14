package com.minseoklim.toyproject2024.payment.ui.dto

import com.minseoklim.toyproject2024.payment.application.dto.MakeCardPaymentOutput

data class MakeCardPaymentResponse private constructor(
    val id: Long,
    val cardId: Long,
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
