package com.minseoklim.queue.dto

data class TokenResponse(
    val token: String,
    val isAccessible: Boolean,
    val remaining: Long
)
