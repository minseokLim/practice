package com.minseoklim.toyproject2024.payment.dto.ui

import com.minseoklim.toyproject2024.payment.domain.model.Amount
import com.minseoklim.toyproject2024.payment.domain.model.ProductName
import com.minseoklim.toyproject2024.payment.dto.application.CheckOutVerifiedPaymentInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CheckOutVerifiedPaymentRequest(
    @get:Positive(message = Amount.ERR_MSG)
    val amount: Long,

    @get:NotBlank(message = ProductName.ERR_MSG)
    val productName: String
) {
    fun toInput(): CheckOutVerifiedPaymentInput {
        return CheckOutVerifiedPaymentInput(
            amount = amount,
            productName = productName
        )
    }
}
