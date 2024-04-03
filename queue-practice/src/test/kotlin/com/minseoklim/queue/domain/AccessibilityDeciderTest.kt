package com.minseoklim.queue.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AccessibilityDeciderTest {
    private val accessibilityDecider = AccessibilityDecider(10)

    @Test
    fun isAccessible() {
        val result1 = accessibilityDecider.isAccessible(9)
        assertThat(result1).isTrue

        val result2 = accessibilityDecider.isAccessible(10)
        assertThat(result2).isFalse
    }

    @Test
    fun getRemaining() {
        val result1 = accessibilityDecider.getRemaining(9)
        assertThat(result1).isEqualTo(0)

        val result2 = accessibilityDecider.getRemaining(10)
        assertThat(result2).isEqualTo(0)

        val result3 = accessibilityDecider.getRemaining(11)
        assertThat(result3).isEqualTo(1)
    }
}
