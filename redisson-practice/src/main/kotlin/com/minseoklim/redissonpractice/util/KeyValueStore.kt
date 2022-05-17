package com.minseoklim.redissonpractice.util

import java.util.Optional

interface KeyValueStore {
    fun <T> getValue(key: String): Optional<T>
    fun <T> save(key: String, value: T)
    fun <T> executeWithLock(key: String, function: () -> T): T
}
