package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.QueryPaymentOutput

data class QueryPaymentResponse private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun from(output: QueryPaymentOutput): QueryPaymentResponse {
            return QueryPaymentResponse(
                id = output.id,
                amount = output.amount,
                productName = output.productName,
                isCanceled = output.isCanceled
            )
        }
    }
}
