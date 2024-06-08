package com.minseoklim.toyproject2024.auth.filter

import com.minseoklim.toyproject2024.auth.domain.TokenParser
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class JwtFilter(
    private val tokenParser: TokenParser
) : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val accessToken = resolveAccessToken(request as HttpServletRequest)

        if (accessToken.isNotBlank() && tokenParser.isValidAccessToken(accessToken)) {
            val authentication = tokenParser.extractAuthentication(accessToken)
            SecurityContextHolder.getContext().authentication = authentication
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

    companion object {
        private const val AUTHORIZATION_PREFIX = "Bearer "
    }
}
