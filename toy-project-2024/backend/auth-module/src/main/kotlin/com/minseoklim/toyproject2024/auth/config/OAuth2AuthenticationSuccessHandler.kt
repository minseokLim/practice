package com.minseoklim.toyproject2024.auth.config

import com.minseoklim.toyproject2024.auth.application.TokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class OAuth2AuthenticationSuccessHandler(
    private val tokenService: TokenService,
    @Value("\${frontend.base-url}") private val frontendBaseUrl: String
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val tokenResponse = tokenService.createToken(authentication)
        val encodedAccessToken = tokenResponse.accessToken
        val encodedRefreshToken = tokenResponse.refreshToken

        response.sendRedirect("$frontendBaseUrl?accessToken=$encodedAccessToken&refreshToken=$encodedRefreshToken")
    }
}
