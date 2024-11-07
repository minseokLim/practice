package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.domain.model.Amount
import com.minseoklim.toyproject2024.payment.domain.model.ProductName
import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class MakeCardPaymentRequest(
    val cardId: Long,

    @get:Positive(message = Amount.ERR_MSG)
    val amount: Long,

    @get:NotBlank(message = ProductName.ERR_MSG)
    val productName: String
) {
    fun toInput(): MakeCardPaymentInput {
        return MakeCardPaymentInput(
            cardId = cardId,
            amount = amount,
            productName = productName
        )
    }
}
