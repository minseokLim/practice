package com.minseoklim.toyproject2024.card.dto.application

import com.minseoklim.toyproject2024.card.domain.model.Card

data class RegisterCardOutput private constructor(
    val id: Long,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun from(card: Card): RegisterCardOutput {
            return RegisterCardOutput(
                id = checkNotNull(card.id),
                maskedCardNumber = card.cardNumber.maskedValue,
                issuerName = card.issuerName.value
            )
        }
    }
}
