package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.dto.LoginRequest
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginService(
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val createTokenService: CreateTokenService
) {
    fun login(request: LoginRequest): TokenResponse {
        val authenticationManager = authenticationManagerBuilder.getObject()
        val authentication = authenticationManager.authenticate(request.toAuthentication())

        return createTokenService.createToken(authentication)
    }
}
