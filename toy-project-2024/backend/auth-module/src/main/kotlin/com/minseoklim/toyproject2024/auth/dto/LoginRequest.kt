package com.minseoklim.toyproject2024.auth.dto

import jakarta.validation.constraints.NotBlank
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

data class LoginRequest(
    @get:NotBlank
    val loginId: String,

    @get:NotBlank
    val password: String
) {
    fun toAuthentication(): Authentication {
        return UsernamePasswordAuthenticationToken(loginId, password)
    }
}
