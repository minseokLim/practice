package com.minseoklim.toyproject2024.auth.ui.dto

import com.minseoklim.toyproject2024.auth.application.dto.LogoutInput
import jakarta.validation.constraints.NotBlank

data class LogoutRequest(
    @get:NotBlank
    val accessToken: String,

    @get:NotBlank
    val refreshToken: String
) {
    fun toInput(): LogoutInput {
        return LogoutInput(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
