package com.minseoklim.toyproject2024.auth.ui.dto

import com.minseoklim.toyproject2024.auth.application.dto.LoginInput
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @get:NotBlank
    val loginId: String,

    @get:NotBlank
    val password: String
) {
    fun toInput(): LoginInput {
        return LoginInput(
            loginId = loginId,
            password = password
        )
    }
}
