package com.minseoklim.toyproject2024.auth.config

import com.minseoklim.toyproject2024.auth.application.CreateTokenService
import com.minseoklim.toyproject2024.auth.application.LoginNotifier
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
import java.time.LocalDateTime

@Configuration
@Transactional
class OAuth2AuthenticationSuccessHandler(
    private val createTokenService: CreateTokenService,
    private val loginHistoryRepository: LoginHistoryRepository,
    private val loginNotifier: LoginNotifier
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val token = createTokenService.createToken(authentication)

        val loginDateTime = LocalDateTime.now()
        val clientIp = request.getClientIp()
        val userAgent = request.getUserAgent()
        loginHistoryRepository.save(
            LoginHistory(
                memberId = authentication.name.toInt(),
                tokenId = token.id,
                clientIp = clientIp,
                userAgent = userAgent,
                socialType = SocialType.valueOf((authentication as OAuth2AuthenticationToken).authorizedClientRegistrationId.uppercase()),
                loginDateTime = loginDateTime
            )
        )
        loginNotifier.notifyLogin(authentication.name.toInt(), clientIp, userAgent, loginDateTime)

        val encodedAccessToken = URLEncoder.encode(token.accessToken, Charsets.UTF_8)
        val encodedRefreshToken = URLEncoder.encode(token.refreshToken, Charsets.UTF_8)
        val redirectUrl = request.session.getAttribute("REDIRECT_URL") as String

        response.sendRedirect("$redirectUrl?accessToken=$encodedAccessToken&refreshToken=$encodedRefreshToken")
    }
}
