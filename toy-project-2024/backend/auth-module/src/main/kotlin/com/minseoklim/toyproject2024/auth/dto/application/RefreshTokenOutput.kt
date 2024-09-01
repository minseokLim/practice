package com.minseoklim.toyproject2024.auth.dto.application

data class RefreshTokenOutput(
    val accessToken: String,
    val refreshToken: String
)
