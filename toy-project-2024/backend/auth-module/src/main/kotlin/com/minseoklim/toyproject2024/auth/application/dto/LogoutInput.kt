package com.minseoklim.toyproject2024.auth.application.dto

data class LogoutInput(
    val accessToken: String,
    val refreshToken: String
)
