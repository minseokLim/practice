package com.minseoklim.toyproject2024.payment.domain.model

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("CARD")
class CardPayment(
    amount: Long,
    productName: String,
    memberId: Long,
    cardId: Long
) : Payment(amount, productName, memberId) {
    val cardId: Long = cardId
}
