package com.minseoklim.toyproject2024.ui

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute

@ControllerAdvice
class GlobalControllerAdvice(
    @Value("\${api-base-url}") private val apiBaseUrl: String
) {
    // 모든 ModelAttribute에 apiBaseUrl을 추가
    @ModelAttribute("apiBaseUrl")
    fun addApiBaseUrl(): String {
        return apiBaseUrl
    }
}
