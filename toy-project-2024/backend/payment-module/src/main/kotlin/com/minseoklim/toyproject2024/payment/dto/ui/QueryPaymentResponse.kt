package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.dto.application.QueryPaymentOutput

data class QueryPaymentResponse private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun of(output: QueryPaymentOutput): QueryPaymentResponse {
            return with(output) {
                QueryPaymentResponse(
                    id = id,
                    amount = amount,
                    productName = productName,
                    isCanceled = isCanceled
                )
            }
        }
    }
}
