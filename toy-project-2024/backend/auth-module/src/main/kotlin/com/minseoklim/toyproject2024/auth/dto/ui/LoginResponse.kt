package com.minseoklim.toyproject2024.auth.dto.ui

import com.minseoklim.toyproject2024.auth.dto.application.LoginOutput

data class LoginResponse private constructor(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(output: LoginOutput): LoginResponse {
            return LoginResponse(
                accessToken = output.accessToken,
                refreshToken = output.refreshToken
            )
        }
    }
}
