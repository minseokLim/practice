package com.minseoklim.toyproject2024.config

import com.minseoklim.toyproject2024.util.AesUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class OAuth2AuthenticationSuccessHandler(
    private val aesUtil: AesUtil
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val socialType = (authentication as OAuth2AuthenticationToken).authorizedClientRegistrationId.uppercase()
        val encryptedSocialId = aesUtil.encrypt(authentication.name)
        response.sendRedirect("/social-link?socialId=$encryptedSocialId&socialType=$socialType")
    }
}
