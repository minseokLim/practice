package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.event.DomainEvent
import org.springframework.context.ApplicationEventPublisher

object EventPublisher {
    private lateinit var publisher: ApplicationEventPublisher

    fun init(publisher: ApplicationEventPublisher) {
        EventPublisher.publisher = publisher
    }

    fun publish(event: DomainEvent) {
        if (EventPublisher::publisher.isInitialized) {
            publisher.publishEvent(event)
        }
    }
}
