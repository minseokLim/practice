package com.minseoklim.toyproject2024.auth.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
