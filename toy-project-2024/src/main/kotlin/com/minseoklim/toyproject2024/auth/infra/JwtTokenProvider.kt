package com.minseoklim.toyproject2024.auth.infra

import com.minseoklim.toyproject2024.auth.domain.TokenProvider
import com.minseoklim.toyproject2024.auth.domain.TokenType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}") secretKey: String,
    @Value("\${jwt.access-token-validity-in-milliseconds}") private val accessTokenValidityInMilliseconds: Long,
    @Value("\${jwt.refresh-token-validity-in-milliseconds}") private val refreshTokenValidityInMilliseconds: Long
) : TokenProvider {
    private val secretKey: Key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    override fun createAccessToken(authentication: Authentication, id: String): String {
        val now = Date()
        val validity = Date(now.time + accessTokenValidityInMilliseconds)

        return Jwts.builder()
            .id(id)
            .subject(authentication.name)
            .claim(TOKEN_TYPE_KEY, TokenType.ACCESS)
            .signWith(secretKey)
            .issuedAt(now)
            .expiration(validity)
            .compact()
    }

    override fun createRefreshToken(id: String): String {
        val now = Date()
        val validity = Date(now.time + refreshTokenValidityInMilliseconds)

        return Jwts.builder()
            .id(id)
            .claim(TOKEN_TYPE_KEY, TokenType.REFRESH)
            .signWith(secretKey)
            .issuedAt(now)
            .expiration(validity)
            .compact()
    }

    companion object {
        const val TOKEN_TYPE_KEY = "typ"
    }
}
