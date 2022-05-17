package com.minseoklim.cqrspractice.event

import java.time.LocalDateTime
import java.util.UUID

abstract class Event {
    val id = UUID.randomUUID()
    val createdDate = LocalDateTime.now()
}
