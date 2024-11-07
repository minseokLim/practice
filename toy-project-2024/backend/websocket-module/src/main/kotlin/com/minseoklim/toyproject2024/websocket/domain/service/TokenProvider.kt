package com.minseoklim.toyproject2024.websocket.domain.service

interface TokenProvider {
    fun createToken(memberId: Long): String
}
