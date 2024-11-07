package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteCardService(
    private val cardRepository: CardRepository
) {
    fun delete(
        memberId: Long,
        cardId: Long
    ) {
        val card = CardServiceHelper.getCard(cardRepository, cardId)
        card.checkAuthority(memberId)
        card.delete()
    }
}
