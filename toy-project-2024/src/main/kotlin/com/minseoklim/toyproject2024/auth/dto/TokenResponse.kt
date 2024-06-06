package com.minseoklim.toyproject2024.auth.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
