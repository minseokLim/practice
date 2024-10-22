package com.minseoklim.toyproject2024.notification.dto.ui

import com.minseoklim.toyproject2024.notification.dto.application.SendNotificationInput
import jakarta.validation.constraints.NotBlank

data class SendNotificationRequest(
    @get:NotBlank
    val title: String,

    @get:NotBlank
    val body: String,

    val imageUrl: String?
) {
    fun toInput(): SendNotificationInput {
        return SendNotificationInput(
            title = title,
            body = body,
            imageUrl = imageUrl
        )
    }
}
