package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.event.DomainEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher

class EventPublisherTest {
    private lateinit var publisher: ApplicationEventPublisher

    @BeforeEach
    fun setUp() {
        publisher = mockk()
    }

    @Test
    fun publish() {
        // given
        val event = mockk<DomainEvent>(relaxed = true)
        every { publisher.publishEvent(event) } returns Unit
        EventPublisher.init(publisher)

        // when
        EventPublisher.publish(event)

        // then
        verify { publisher.publishEvent(event) }
    }
}
