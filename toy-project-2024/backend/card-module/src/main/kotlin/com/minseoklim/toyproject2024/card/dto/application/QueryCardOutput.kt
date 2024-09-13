package com.minseoklim.toyproject2024.card.dto.application

import com.minseoklim.toyproject2024.card.domain.model.Card
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil

data class QueryCardOutput private constructor(
    val id: Int,
    val maskedCardNumber: String,
    val cardNumber: String,
    val cardExpiry: String,
    val birth: String,
    val pwd2digit: String,
    val issuerName: String
) {
    companion object {
        fun of(card: Card): QueryCardOutput {
            return QueryCardOutput(
                id = checkNotNull(card.id),
                maskedCardNumber = card.cardNumber.maskedValue,
                cardNumber = TextEncryptUtil.decrypt(card.cardNumber.encryptedValue),
                cardExpiry = TextEncryptUtil.decrypt(card.cardExpiry.encryptedValue),
                birth = TextEncryptUtil.decrypt(card.birth.encryptedValue),
                pwd2digit = TextEncryptUtil.decrypt(card.pwd2digit.encryptedValue),
                issuerName = card.issuerName.value
            )
        }
    }
}
