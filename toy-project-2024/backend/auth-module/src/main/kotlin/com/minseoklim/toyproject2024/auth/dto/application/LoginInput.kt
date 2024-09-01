package com.minseoklim.toyproject2024.auth.dto.application

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

data class LoginInput(
    val loginId: String,
    val password: String
) {
    fun toAuthentication(): Authentication {
        return UsernamePasswordAuthenticationToken(loginId, password)
    }
}
