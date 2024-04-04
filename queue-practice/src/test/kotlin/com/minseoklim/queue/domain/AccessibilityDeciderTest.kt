package com.minseoklim.queue.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AccessibilityDeciderTest {

    @Autowired
    private lateinit var accessLimitDecider: AccessLimitDecider

    private lateinit var accessibilityDecider: AccessibilityDecider

    @BeforeEach
    fun setUp() {
        accessibilityDecider = AccessibilityDecider(accessLimitDecider)
    }

    @Test
    fun isAccessible() {
        // given
        accessLimitDecider.setAccessLimit(10)

        // when
        val result1 = accessibilityDecider.isAccessible(9)

        // then
        assertThat(result1).isTrue

        // when
        val result2 = accessibilityDecider.isAccessible(10)

        // then
        assertThat(result2).isFalse
    }

    @Test
    fun getRemaining() {
        // given
        accessLimitDecider.setAccessLimit(10)

        // when
        val result1 = accessibilityDecider.getRemaining(9)

        // then
        assertThat(result1).isEqualTo(0)

        // when
        val result2 = accessibilityDecider.getRemaining(10)

        // then
        assertThat(result2).isEqualTo(0)

        // when
        val result3 = accessibilityDecider.getRemaining(11)

        // then
        assertThat(result3).isEqualTo(1)
    }
}
