package com.minseoklim.toyproject2024.websocket.application

import com.minseoklim.toyproject2024.websocket.domain.service.TokenProvider
import org.springframework.stereotype.Service

@Service
class IssueTokenService(
    private val tokenProvider: TokenProvider
) {
    fun issue(memberId: Long): String {
        return tokenProvider.createToken(memberId)
    }
}
