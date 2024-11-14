package com.minseoklim.toyproject2024.notification.ui.dto

import jakarta.validation.constraints.NotBlank

data class RegisterPushTokenRequest(
    @get:NotBlank
    val token: String
)
