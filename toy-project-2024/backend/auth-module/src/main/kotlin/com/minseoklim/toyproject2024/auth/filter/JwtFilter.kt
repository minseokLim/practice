package com.minseoklim.toyproject2024.auth.filter

import com.minseoklim.toyproject2024.auth.domain.repository.AccessTokenDbCheckFlagRepository
import com.minseoklim.toyproject2024.auth.domain.repository.TokenRepository
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class JwtFilter(
    private val tokenParser: TokenParser,
    private val accessTokenDbCheckFlagRepository: AccessTokenDbCheckFlagRepository,
    private val tokenRepository: TokenRepository
) : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val accessToken = resolveAccessToken(request as HttpServletRequest)

        if (accessToken.isNotBlank() && tokenParser.isValidAccessToken(accessToken)) {
            val authentication = tokenParser.extractAuthentication(accessToken)
            setAuthentication(authentication, accessToken)
        }

        chain.doFilter(request, response)
    }

    private fun resolveAccessToken(request: HttpServletRequest): String {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorization?.isNotBlank() == true &&
            authorization.lowercase().startsWith(AUTHORIZATION_PREFIX.lowercase())
        ) {
            return authorization.substring(AUTHORIZATION_PREFIX.length)
        }
        return ""
    }

    private fun setAuthentication(authentication: Authentication, accessToken: String) {
        if (accessTokenDbCheckFlagRepository.existsById(authentication.name.toInt())) {
            val accessTokenId = tokenParser.extractId(accessToken)
            if (tokenRepository.existsById(accessTokenId)) {
                SecurityContextHolder.getContext().authentication = authentication
            }
        } else {
            SecurityContextHolder.getContext().authentication = authentication
        }
    }

    companion object {
        private const val AUTHORIZATION_PREFIX = "Bearer "
    }
}
