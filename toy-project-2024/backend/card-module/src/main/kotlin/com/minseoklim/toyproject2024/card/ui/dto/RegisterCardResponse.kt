package com.minseoklim.toyproject2024.card.ui.dto

import com.minseoklim.toyproject2024.card.application.dto.RegisterCardOutput

data class RegisterCardResponse private constructor(
    val id: Long,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun from(output: RegisterCardOutput): RegisterCardResponse {
            return RegisterCardResponse(
                id = output.id,
                maskedCardNumber = output.maskedCardNumber,
                issuerName = output.issuerName
            )
        }
    }
}
