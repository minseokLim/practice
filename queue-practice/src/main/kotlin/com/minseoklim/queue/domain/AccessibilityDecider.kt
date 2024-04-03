package com.minseoklim.queue.domain

import org.springframework.stereotype.Component

@Component
class AccessibilityDecider(
    private val accessLimit: Long = 1000L
) {
    fun isAccessible(rank: Long): Boolean {
        return rank < accessLimit
    }

    fun getRemaining(rank: Long): Long {
        val remaining = rank - accessLimit
        return if (remaining < 0) 0 else remaining
    }
}
