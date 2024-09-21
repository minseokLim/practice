package com.minseoklim.toyproject2024.websocket.infra

import com.minseoklim.toyproject2024.websocket.domain.service.TokenProvider
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

@Component
class JwtWebSocketTokenProvider(
    @Value("\${websocket.secret-key}") secretKey: String,
    @Value("\${websocket.token-validity-in-milliseconds}") private val tokenValidityInMilliseconds: Long
) : TokenProvider {
    private val secretKey: Key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    override fun createToken(memberId: Int): String {
        val now = Date()
        val validity = Date(now.time + tokenValidityInMilliseconds)

        return Jwts.builder()
            .subject(memberId.toString())
            .signWith(secretKey)
            .issuedAt(now)
            .expiration(validity)
            .compact()
    }
}
