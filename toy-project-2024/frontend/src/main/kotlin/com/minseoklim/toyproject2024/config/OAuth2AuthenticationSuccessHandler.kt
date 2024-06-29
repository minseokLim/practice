package com.minseoklim.toyproject2024.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class OAuth2AuthenticationSuccessHandler(
    private val textEncryptor: TextEncryptor
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val socialType = (authentication as OAuth2AuthenticationToken).authorizedClientRegistrationId.uppercase()
        val encryptedSocialId = textEncryptor.encrypt(authentication.name)
        response.sendRedirect("/social-link?socialId=$encryptedSocialId&socialType=$socialType")
    }
}
