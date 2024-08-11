package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.card.dto.QueryCardResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryCardService(
    private val cardRepository: CardRepository
) {
    fun list(memberId: Int, pageable: Pageable): Page<QueryCardResponse> {
        val cards = cardRepository.findAllByMemberId(memberId, pageable)
        return cards.map { QueryCardResponse.of(it) }
    }
}
