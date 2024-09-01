package com.minseoklim.toyproject2024.auth.dto.ui

import com.minseoklim.toyproject2024.auth.dto.application.LogoutInput
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
