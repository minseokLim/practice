package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.Amount
import com.minseoklim.toyproject2024.payment.domain.model.ProductName
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CheckOutVerifiedPaymentRequest(
    @get:Positive(message = Amount.ERR_MSG)
    val amount: Long,

    @get:NotBlank(message = ProductName.ERR_MSG)
    val productName: String
) {
    fun toEntity(memberId: Int): VerifiedPayment {
        return VerifiedPayment(
            amount = amount,
            productName = productName,
            memberId = memberId
        )
    }
}
