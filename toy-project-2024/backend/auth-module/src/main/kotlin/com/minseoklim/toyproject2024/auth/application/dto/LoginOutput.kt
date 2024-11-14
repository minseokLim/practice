package com.minseoklim.toyproject2024.auth.application.dto

data class LoginOutput(
    val accessToken: String,
    val refreshToken: String
)
