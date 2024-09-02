package com.minseoklim.toyproject2024.card.dto.application

import com.minseoklim.toyproject2024.card.domain.model.Card

data class QueryCardOutput private constructor(
    val id: Int,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun of(card: Card): QueryCardOutput {
            return with(card) {
                QueryCardOutput(
                    id = id!!,
                    maskedCardNumber = cardNumber.maskedValue,
                    issuerName = issuerName.value
                )
            }
        }
    }
}
