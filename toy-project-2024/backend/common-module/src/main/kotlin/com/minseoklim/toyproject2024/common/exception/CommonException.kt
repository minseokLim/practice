package com.minseoklim.toyproject2024.common.exception

abstract class CommonException(
    val code: String,
    message: String
) : RuntimeException(message) {
    override fun fillInStackTrace(): Throwable = this
}
