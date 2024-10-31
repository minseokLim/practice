package com.minseoklim.toyproject2024.websocket.infra

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.websocket.domain.service.TokenParser
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class JwtWebSocketTokenParser(
    @Value("\${websocket.secret-key}") secretKey: String
) : TokenParser {
    private val jwtParser = Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)))
        .build()

    override fun extractMemberId(token: String): String {
        try {
            val claims = jwtParser.parseSignedClaims(token).payload
            return claims.subject
        } catch (e: JwtException) {
            throw BadRequestException(ErrorCode.INVALID_TOKEN)
        }
    }

    override fun isValidToken(token: String): Boolean {
        try {
            jwtParser.parseSignedClaims(token)
            return true
        } catch (e: JwtException) {
            return false
        }
    }
}
