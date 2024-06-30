package com.minseoklim.toyproject2024.auth.domain.service

import java.util.UUID

object TokenIdGenerator {
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
