package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.card.dto.RegisterCardRequest
import com.minseoklim.toyproject2024.card.dto.RegisterCardResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterCardService(
    private val cardRepository: CardRepository
) {
    fun register(memberId: Int, request: RegisterCardRequest): RegisterCardResponse {
        val card = cardRepository.save(request.toEntity(memberId))
        return RegisterCardResponse.of(card)
    }
}
