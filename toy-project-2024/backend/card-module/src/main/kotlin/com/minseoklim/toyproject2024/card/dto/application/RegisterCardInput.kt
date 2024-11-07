package com.minseoklim.toyproject2024.card.dto.application

import com.minseoklim.toyproject2024.card.domain.model.Card

data class RegisterCardInput(
    val cardNumber: String,
    val cardExpiry: String,
    val birth: String,
    val pwd2digit: String,
    val issuerName: String
) {
    fun toEntity(memberId: Long): Card {
        return Card(
            cardNumber = cardNumber,
            cardExpiry = cardExpiry,
            birth = birth,
            pwd2digit = pwd2digit,
            issuerName = issuerName,
            memberId = memberId
        )
    }
}
