package com.minseoklim.toyproject2024.auth.infra

import com.minseoklim.toyproject2024.auth.domain.model.Role
import com.minseoklim.toyproject2024.auth.domain.model.TokenType
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import com.minseoklim.toyproject2024.auth.infra.JwtTokenProvider.Companion.AUTHORITIES_KEY
import com.minseoklim.toyproject2024.auth.infra.JwtTokenProvider.Companion.AUTHORITY_DELIMITER
import com.minseoklim.toyproject2024.auth.infra.JwtTokenProvider.Companion.TOKEN_TYPE_KEY
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date

@Component
class JwtTokenParser(
    @Value("\${jwt.secret-key}") secretKey: String
) : TokenParser {
    private val jwtParser = Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)))
        .build()

    override fun extractAuthentication(accessToken: String): Authentication {
        try {
            val claims = jwtParser.parseSignedClaims(accessToken).payload
            return extractAuthentication(claims)
        } catch (e: ExpiredJwtException) {
            return extractAuthentication(e.claims)
        } catch (e: JwtException) {
            throw BadCredentialsException(e.message)
        }
    }

    override fun extractId(token: String): String {
        try {
            val claims = jwtParser.parseSignedClaims(token).payload
            return claims.id
        } catch (e: ExpiredJwtException) {
            return e.claims.id
        } catch (e: JwtException) {
            throw BadCredentialsException(e.message)
        }
    }

    override fun isValidAccessToken(accessToken: String): Boolean {
        return isValidToken(accessToken, TokenType.ACCESS)
    }

    override fun isValidRefreshToken(refreshToken: String): Boolean {
        return isValidToken(refreshToken, TokenType.REFRESH)
    }

    private fun extractAuthentication(claims: Claims): Authentication {
        val authorities = extractAuthorities(claims)

        val principal = User.builder()
            .username(claims.subject)
            .password("N/A")
            .authorities(authorities)
            .build()

        return UsernamePasswordAuthenticationToken(principal, null, authorities)
    }

    private fun extractAuthorities(claims: Claims): Set<GrantedAuthority> {
        return claims[AUTHORITIES_KEY]
            .toString()
            .split(AUTHORITY_DELIMITER)
            .filter { it.isNotBlank() }
            .map { Role.of(it) }
            .toSet()
    }

    private fun isValidToken(token: String, tokenType: TokenType): Boolean {
        try {
            val claims = jwtParser.parseSignedClaims(token).payload
            val extractedType = TokenType.valueOf(claims[TOKEN_TYPE_KEY, String::class.java])

            return !claims.expiration.before(Date()) && extractedType === tokenType
        } catch (e: JwtException) {
            return false
        }
    }
}
