package com.minseoklim.toyproject2024.auth.infra

import com.minseoklim.toyproject2024.auth.domain.model.TokenType
import com.minseoklim.toyproject2024.auth.domain.service.TokenProvider
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

@Component
class JwtAuthTokenProvider(
    @Value("\${auth.secret-key}") secretKey: String,
    @Value("\${auth.access-token-validity-in-milliseconds}") private val accessTokenValidityInMilliseconds: Long,
    @Value("\${auth.refresh-token-validity-in-milliseconds}") private val refreshTokenValidityInMilliseconds: Long
) : TokenProvider {
    private val secretKey: Key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    override fun createAccessToken(
        authentication: Authentication,
        id: String
    ): String {
        val now = Date()
        val validity = Date(now.time + accessTokenValidityInMilliseconds)

        return Jwts.builder()
            .id(id)
            .subject(authentication.name)
            .claim(AUTHORITIES_KEY, toString(authentication.authorities))
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

    private fun toString(authorities: Collection<GrantedAuthority>): String {
        return authorities.joinToString(AUTHORITY_DELIMITER) { it.authority }
    }

    companion object {
        const val AUTHORITIES_KEY = "auth"
        const val AUTHORITY_DELIMITER = ","
        const val TOKEN_TYPE_KEY = "typ"
    }
}
