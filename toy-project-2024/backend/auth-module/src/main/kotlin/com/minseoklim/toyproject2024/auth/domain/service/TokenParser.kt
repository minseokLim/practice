package com.minseoklim.toyproject2024.auth.domain.service

import org.springframework.security.core.Authentication

interface TokenParser {
    fun extractAuthentication(accessToken: String): Authentication

    fun extractId(token: String): String

    fun isValidAccessToken(accessToken: String): Boolean

    fun isValidRefreshToken(refreshToken: String): Boolean
}
