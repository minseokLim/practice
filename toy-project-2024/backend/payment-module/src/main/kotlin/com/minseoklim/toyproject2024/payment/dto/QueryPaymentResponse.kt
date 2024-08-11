package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.Payment

data class QueryPaymentResponse private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun of(cardPayment: Payment): QueryPaymentResponse {
            return with(cardPayment) {
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
