package com.minseoklim.toyproject2024.auth.dto.application

data class LogoutInput(
    val accessToken: String,
    val refreshToken: String
)
