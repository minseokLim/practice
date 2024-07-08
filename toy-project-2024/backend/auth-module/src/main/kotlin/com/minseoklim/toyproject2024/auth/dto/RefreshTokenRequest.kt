package com.minseoklim.toyproject2024.auth.dto

import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @get:NotBlank
    val accessToken: String,

    @get:NotBlank
    val refreshToken: String
)
