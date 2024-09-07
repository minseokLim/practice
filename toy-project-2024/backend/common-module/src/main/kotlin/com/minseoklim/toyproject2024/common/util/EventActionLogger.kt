package com.minseoklim.toyproject2024.common.util

import com.minseoklim.toyproject2024.common.event.DomainEvent
import mu.KLogging

object EventActionLogger : KLogging() {
    fun executeWithLogging(event: DomainEvent, action: () -> Unit) {
        try {
            action()
        } catch (e: Exception) {
            val eventJson = JacksonUtil.objectWriter.writeValueAsString(event)
            logger.error("Failed to execute action for event: ${event.javaClass} $eventJson", e)
        }
    }
}
