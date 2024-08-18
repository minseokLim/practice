package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.Payment

data class QueryPaymentResponse private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun of(payment: Payment): QueryPaymentResponse {
            return with(payment) {
                QueryPaymentResponse(
                    id = id!!,
                    amount = amount.value.toLong(),
                    productName = productName.value,
                    isCanceled = isCanceled
                )
            }
        }
    }
}
