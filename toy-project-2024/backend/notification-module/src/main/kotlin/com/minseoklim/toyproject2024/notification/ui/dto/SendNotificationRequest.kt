package com.minseoklim.toyproject2024.notification.ui.dto

import com.minseoklim.toyproject2024.notification.application.dto.SendNotificationInput
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
