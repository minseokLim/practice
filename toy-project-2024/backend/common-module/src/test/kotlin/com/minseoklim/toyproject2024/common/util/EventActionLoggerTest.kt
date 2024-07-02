package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.event.DomainEvent
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EventActionLoggerTest {
    private val logger = EventActionLogger.logger

    @BeforeEach
    fun setUp() {
        mockkObject(logger)
    }

    @Test
    fun executeWithLogging() {
        // given
        val event = object : DomainEvent(this) {}

        // when, then
        assertThatNoException().isThrownBy {
            EventActionLogger.executeWithLogging(event) {
                // do nothing
            }
        }

        // given
        every { logger.error(any<String>(), any<Throwable>()) } returns Unit

        // when
        EventActionLogger.executeWithLogging(event) {
            throw RuntimeException()
        }

        // then
        verify { logger.error(any<String>(), any<Throwable>()) }
    }
}
