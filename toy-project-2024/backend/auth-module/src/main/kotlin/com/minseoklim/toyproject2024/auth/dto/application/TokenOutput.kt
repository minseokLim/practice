package com.minseoklim.toyproject2024.auth.dto.application

data class TokenOutput(
    val id: String,
    val accessToken: String,
    val refreshToken: String
)
