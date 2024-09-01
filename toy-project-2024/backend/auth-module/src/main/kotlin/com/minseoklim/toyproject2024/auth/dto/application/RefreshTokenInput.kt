package com.minseoklim.toyproject2024.auth.dto.application

data class RefreshTokenInput(
    val accessToken: String,
    val refreshToken: String
)
