package com.minseoklim.toyproject2024.auth.domain

import org.springframework.security.core.Authentication

interface TokenProvider {
    fun createAccessToken(authentication: Authentication, id: String): String
    fun createRefreshToken(id: String): String
}
