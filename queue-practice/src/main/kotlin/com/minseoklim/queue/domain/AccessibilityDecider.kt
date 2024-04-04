package com.minseoklim.queue.domain

import org.springframework.stereotype.Component

@Component
class AccessibilityDecider(
    private val accessLimitDecider: AccessLimitDecider
) {
    fun isAccessible(rank: Long): Boolean {
        return rank < accessLimitDecider.getAccessLimit()
    }

    fun getRemaining(rank: Long): Long {
        val remaining = rank - accessLimitDecider.getAccessLimit()
        return if (remaining < 0) 0 else remaining
    }
}
