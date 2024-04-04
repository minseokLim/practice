package com.minseoklim.queue.domain

interface AccessLimitDecider {
    fun getAccessLimit(): Long
    fun setAccessLimit(accessLimit: Long)
}
