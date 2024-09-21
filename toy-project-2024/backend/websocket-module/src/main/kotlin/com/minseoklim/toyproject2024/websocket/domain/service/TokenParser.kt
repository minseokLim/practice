package com.minseoklim.toyproject2024.websocket.domain.service

interface TokenParser {
    fun extractMemberId(token: String): String

    fun isValidToken(token: String): Boolean
}
