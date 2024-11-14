package com.minseoklim.toyproject2024.auth.ui.dto

import com.minseoklim.toyproject2024.auth.application.dto.LoginOutput

data class LoginResponse private constructor(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(output: LoginOutput): LoginResponse {
            return LoginResponse(
                accessToken = output.accessToken,
                refreshToken = output.refreshToken
            )
        }
    }
}
