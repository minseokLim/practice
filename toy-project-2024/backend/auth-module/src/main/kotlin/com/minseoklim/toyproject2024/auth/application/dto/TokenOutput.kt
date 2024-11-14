package com.minseoklim.toyproject2024.auth.application.dto

data class TokenOutput(
    val id: String,
    val accessToken: String,
    val refreshToken: String
)
