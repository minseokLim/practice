package com.minseoklim.toyproject2024.card.dto.ui

import com.minseoklim.toyproject2024.card.dto.application.RegisterCardOutput

data class RegisterCardResponse private constructor(
    val id: Int,
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
