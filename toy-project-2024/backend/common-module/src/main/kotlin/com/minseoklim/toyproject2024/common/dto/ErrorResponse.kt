package com.minseoklim.toyproject2024.common.dto

import com.minseoklim.toyproject2024.common.exception.CommonException

data class ErrorResponse(
    val code: String,
    val message: String? = null
) {
    companion object {
        fun from(e: CommonException): ErrorResponse {
            return ErrorResponse(e.code.name, e.message)
        }
    }
}
