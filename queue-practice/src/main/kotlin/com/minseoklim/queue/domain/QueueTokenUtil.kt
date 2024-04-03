package com.minseoklim.queue.domain

import java.time.LocalDateTime

interface QueueTokenUtil {
    fun createToken(): String
    fun rankToken(token: String): Long
    fun deleteToken(token: String)
    fun deleteTokenBefore(time: LocalDateTime)
    fun initialize()
}
