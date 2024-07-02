package com.minseoklim.toyproject2024.common.event

import org.springframework.context.ApplicationEvent

abstract class DomainEvent(
    source: Any
) : ApplicationEvent(source)
