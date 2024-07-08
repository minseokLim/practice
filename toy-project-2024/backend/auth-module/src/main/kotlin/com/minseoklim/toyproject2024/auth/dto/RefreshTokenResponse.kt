package com.minseoklim.toyproject2024.auth.dto

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
