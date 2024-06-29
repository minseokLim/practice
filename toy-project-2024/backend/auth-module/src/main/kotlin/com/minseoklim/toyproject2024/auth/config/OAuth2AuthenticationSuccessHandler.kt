package com.minseoklim.toyproject2024.auth.config

import com.minseoklim.toyproject2024.auth.application.CreateTokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class OAuth2AuthenticationSuccessHandler(
    private val createTokenService: CreateTokenService
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val tokenResponse = createTokenService.createToken(authentication)
        val encodedAccessToken = tokenResponse.accessToken
        val encodedRefreshToken = tokenResponse.refreshToken

        val redirectUrl = request.session.getAttribute("REDIRECT_URL") as String

        response.sendRedirect("$redirectUrl?accessToken=$encodedAccessToken&refreshToken=$encodedRefreshToken")
    }
}
