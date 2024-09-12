package com.minseoklim.toyproject2024.card.dto.application

import com.minseoklim.toyproject2024.card.domain.model.Card

data class RegisterCardOutput private constructor(
    val id: Int,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun of(card: Card): RegisterCardOutput {
            return with(card) {
                RegisterCardOutput(
                    id = checkNotNull(id),
                    maskedCardNumber = cardNumber.maskedValue,
                    issuerName = issuerName.value
                )
            }
        }
    }
}
