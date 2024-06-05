package com.minseoklim.toyproject2024.auth.domain

import org.springframework.security.core.Authentication

interface TokenProvider {
    fun createAccessToken(authentication: Authentication): String
    fun createRefreshToken(): String
}
