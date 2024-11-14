package com.minseoklim.toyproject2024.payment.ui.dto

import com.minseoklim.toyproject2024.payment.application.dto.MakeCardPaymentInput
import com.minseoklim.toyproject2024.payment.domain.model.Amount
import com.minseoklim.toyproject2024.payment.domain.model.ProductName
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
