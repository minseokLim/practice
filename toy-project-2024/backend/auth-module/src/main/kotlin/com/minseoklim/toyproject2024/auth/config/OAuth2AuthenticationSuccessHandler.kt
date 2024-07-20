package com.minseoklim.toyproject2024.auth.config

import com.minseoklim.toyproject2024.auth.application.CreateTokenService
import com.minseoklim.toyproject2024.auth.domain.model.LoginHistory
import com.minseoklim.toyproject2024.auth.domain.repository.LoginHistoryRepository
import com.minseoklim.toyproject2024.common.util.ClientUtil.getClientIp
import com.minseoklim.toyproject2024.common.util.ClientUtil.getUserAgent
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.transaction.annotation.Transactional
import java.net.URLEncoder

@Configuration
@Transactional
class OAuth2AuthenticationSuccessHandler(
    private val createTokenService: CreateTokenService,
    private val loginHistoryRepository: LoginHistoryRepository
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val tokenResponse = createTokenService.createToken(authentication)
        loginHistoryRepository.save(
            LoginHistory(
                memberId = authentication.name.toInt(),
                tokenId = tokenResponse.id,
                clientIp = request.getClientIp(),
                userAgent = request.getUserAgent(),
                socialType = SocialType.valueOf((authentication as OAuth2AuthenticationToken).authorizedClientRegistrationId.uppercase())
            )
        )

        val encodedAccessToken = URLEncoder.encode(tokenResponse.accessToken, Charsets.UTF_8)
        val encodedRefreshToken = URLEncoder.encode(tokenResponse.refreshToken, Charsets.UTF_8)

        val redirectUrl = request.session.getAttribute("REDIRECT_URL") as String

        response.sendRedirect("$redirectUrl?accessToken=$encodedAccessToken&refreshToken=$encodedRefreshToken")
    }
}
