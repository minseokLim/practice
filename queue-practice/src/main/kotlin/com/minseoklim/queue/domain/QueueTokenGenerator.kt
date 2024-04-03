package com.minseoklim.queue.domain

import java.util.UUID

object QueueTokenGenerator {
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
