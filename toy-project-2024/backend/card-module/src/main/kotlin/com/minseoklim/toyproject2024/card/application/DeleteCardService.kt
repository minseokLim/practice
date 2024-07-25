package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.model.Card
import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteCardService(
    private val cardRepository: CardRepository
) {
    fun delete(memberId: Int, cardId: Int) {
        val card = getCard(cardId)
        card.checkAuthority(memberId)
        card.delete()
    }

    private fun getCard(cardId: Int): Card {
        return cardRepository.findById(cardId)
            .orElseThrow { NotFoundException("CARD_NOT_FOUND", "찾을 수 없는 카드입니다.") }
    }
}
