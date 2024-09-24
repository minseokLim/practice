package com.minseoklim.toyproject2024.payment.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.Payment

data class QueryPaymentOutput private constructor(
    val id: Int,
    val amount: Long,
    val productName: String,
    val isCanceled: Boolean
) {
    companion object {
        fun from(payment: Payment): QueryPaymentOutput {
            return QueryPaymentOutput(
                id = checkNotNull(payment.id),
                amount = payment.amount.value.toLong(),
                productName = payment.productName.value,
                isCanceled = payment.isCanceled
            )
        }
    }
}
