package com.minseoklim.toyproject2024.auth.domain

import org.springframework.security.core.Authentication

interface TokenParser {
    fun extractAuthentication(accessToken: String): Authentication
    fun extractId(token: String): String
    fun validateAccessToken(accessToken: String): Boolean
    fun validateRefreshToken(refreshToken: String): Boolean
}
