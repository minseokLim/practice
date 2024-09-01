package com.minseoklim.toyproject2024.auth.dto.ui

import com.minseoklim.toyproject2024.auth.dto.application.RefreshTokenInput
import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @get:NotBlank
    val accessToken: String,

    @get:NotBlank
    val refreshToken: String
) {
    fun toInput(): RefreshTokenInput {
        return RefreshTokenInput(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
