package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import com.minseoklim.toyproject2024.card.dto.application.RegisterCardInput
import com.minseoklim.toyproject2024.card.dto.application.RegisterCardOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterCardService(
    private val cardRepository: CardRepository
) {
    fun register(memberId: Int, input: RegisterCardInput): RegisterCardOutput {
        val card = cardRepository.save(input.toEntity(memberId))
        return RegisterCardOutput.of(card)
    }
}
