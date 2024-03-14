package com.minseoklim.queue.dto

import com.minseoklim.queue.exception.CommonException

data class ErrorResponse(
    val code: String,
    val message: String? = null
) {
    companion object {
        fun of(e: CommonException): ErrorResponse {
            return ErrorResponse(e.code, e.message)
        }
    }
}
