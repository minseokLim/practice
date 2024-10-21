package com.minseoklim.toyproject2024.notification.dto.ui

import com.minseoklim.toyproject2024.notification.dto.application.SendNotificationInput

data class SendNotificationRequest(
    val title: String,
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
