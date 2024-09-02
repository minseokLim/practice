package com.minseoklim.toyproject2024.card.dto.ui

import com.minseoklim.toyproject2024.card.dto.application.RegisterCardOutput

data class RegisterCardResponse private constructor(
    val id: Int,
    val maskedCardNumber: String,
    val issuerName: String
) {
    companion object {
        fun of(output: RegisterCardOutput): RegisterCardResponse {
            return with(output) {
                RegisterCardResponse(
                    id = id,
                    maskedCardNumber = maskedCardNumber,
                    issuerName = issuerName
                )
            }
        }
    }
}
