package com.minseoklim.toyproject2024.auth.dto.application

data class LoginOutput(
    val accessToken: String,
    val refreshToken: String
)
