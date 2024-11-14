package com.minseoklim.toyproject2024.auth.application.dto

data class RefreshTokenInput(
    val accessToken: String,
    val refreshToken: String
)
