package com.minseoklim.toyproject2024.common.exception

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode

abstract class CommonException(
    val code: ErrorCode
) : RuntimeException(code.message) {
    override fun fillInStackTrace(): Throwable = this
}
