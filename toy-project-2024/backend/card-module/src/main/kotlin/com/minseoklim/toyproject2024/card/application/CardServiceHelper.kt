package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.model.Card
import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NotFoundException

object CardServiceHelper {
    fun getCard(
        cardRepository: CardRepository,
        cardId: Int
    ): Card {
        return cardRepository.findById(cardId)
            .orElseThrow { NotFoundException(ErrorCode.CARD_NOT_FOUND) }
    }
}
