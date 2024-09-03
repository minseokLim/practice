package com.minseoklim.toyproject2024.payment.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.Payment

data class QueryPaymentOutput private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun of(payment: Payment): QueryPaymentOutput {
            return with(payment) {
                QueryPaymentOutput(
                    id = id!!,
                    amount = amount.value.toLong(),
                    productName = productName.value,
                    isCanceled = isCanceled
                )
            }
        }
    }
}
