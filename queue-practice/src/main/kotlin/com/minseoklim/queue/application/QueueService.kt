package com.minseoklim.queue.application

import com.minseoklim.queue.domain.AccessibilityDecider
import com.minseoklim.queue.domain.QueueTokenUtil
import com.minseoklim.queue.dto.TokenResponse
import org.springframework.stereotype.Service

@Service
class QueueService(
    private val queueTokenUtil: QueueTokenUtil,
    private val accessibilityDecider: AccessibilityDecider
) {
    fun createToken(): TokenResponse {
        val token = queueTokenUtil.createToken()
        val rank = queueTokenUtil.rankToken(token)
        return TokenResponse(token, accessibilityDecider.isAccessible(rank), accessibilityDecider.getRemaining(rank))
    }

    fun checkToken(token: String): TokenResponse {
        val rank = queueTokenUtil.rankToken(token)
        return TokenResponse(token, accessibilityDecider.isAccessible(rank), accessibilityDecider.getRemaining(rank))
    }

    fun deleteToken(token: String) {
        queueTokenUtil.deleteToken(token)
    }
}
