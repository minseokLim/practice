package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.Amount
import com.minseoklim.toyproject2024.payment.domain.model.CardPayment
import com.minseoklim.toyproject2024.payment.domain.model.ProductName
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class MakeCardPaymentRequest(
    val cardId: Int,

    @get:Positive(message = Amount.ERR_MSG)
    val amount: Long,

    @get:NotBlank(message = ProductName.ERR_MSG)
    val productName: String
) {
    fun toEntity(memberId: Int): CardPayment {
        return CardPayment(
            amount = amount,
            productName = productName,
            memberId = memberId,
            cardId = cardId
        )
    }
}
