package com.minseoklim.toyproject2024.auth.application.dto

data class RefreshTokenOutput(
    val accessToken: String,
    val refreshToken: String
)
