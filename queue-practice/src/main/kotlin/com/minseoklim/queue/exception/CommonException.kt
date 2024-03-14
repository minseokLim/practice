package com.minseoklim.queue.exception

abstract class CommonException(
    val code: String,
    message: String
) : RuntimeException(message)
