package com.minseoklim.toyproject2024.common.event

import org.springframework.context.ApplicationEventPublisher

object EventPublisher {
    private lateinit var publisher: ApplicationEventPublisher

    fun init(publisher: ApplicationEventPublisher) {
        this.publisher = publisher
    }

    fun publish(event: DomainEvent) {
        if (::publisher.isInitialized) {
            publisher.publishEvent(event)
        }
    }
}
