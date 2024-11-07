package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.card.dto.application.QueryCardOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryCardService(
    private val cardRepository: CardRepository
) {
    fun list(
        memberId: Long,
        pageable: Pageable
    ): Page<QueryCardOutput> {
        val cards = cardRepository.findAllByMemberId(memberId, pageable)
        return cards.map { QueryCardOutput.from(it) }
    }

    fun get(
        memberId: Long,
        cardId: Long
    ): QueryCardOutput {
        val card = CardServiceHelper.getCard(cardRepository, cardId)
        card.checkAuthority(memberId)
        return QueryCardOutput.from(card)
    }
}
