package com.minseoklim.toyproject2024.common.dto

import com.minseoklim.toyproject2024.common.exception.CommonException

data class ErrorResponse(
    val code: String,
    val message: String? = null
) {
    companion object {
        fun of(e: CommonException): ErrorResponse {
            return with(e) {
                ErrorResponse(code, message)
            }
        }
    }
}
