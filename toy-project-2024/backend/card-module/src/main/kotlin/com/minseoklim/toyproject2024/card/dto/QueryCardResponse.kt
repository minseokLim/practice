package com.minseoklim.toyproject2024.card.dto

import com.minseoklim.toyproject2024.card.domain.model.Card

data class QueryCardResponse(
    val id: Int,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun of(card: Card): RegisterCardResponse {
            return with(card) {
                RegisterCardResponse(
                    id = id!!,
                    maskedCardNumber = cardNumber.maskedValue,
                    issuerName = issuerName.value
                )
            }
        }
    }
}
