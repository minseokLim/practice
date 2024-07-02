package com.minseoklim.toyproject2024.common.config

import com.minseoklim.toyproject2024.common.util.EventPublisher
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableAsync
class EventConfig(
    applicationContext: ApplicationContext
) {
    init {
        EventPublisher.init(applicationContext)
    }
}
