package com.minseoklim.toyproject2024.card.application

import com.minseoklim.toyproject2024.card.application.dto.RegisterCardInput
import com.minseoklim.toyproject2024.card.application.dto.RegisterCardOutput
import com.minseoklim.toyproject2024.card.domain.repository.CardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterCardService(
    private val cardRepository: CardRepository
) {
    fun register(
        memberId: Long,
        input: RegisterCardInput
    ): RegisterCardOutput {
        val card = cardRepository.save(input.toEntity(memberId))
        return RegisterCardOutput.from(card)
    }
}
