package com.minseoklim.toyproject2024.notification.dto.application

import com.minseoklim.toyproject2024.notification.domain.model.Notification

data class SendNotificationInput(
    val title: String,
    val body: String,
    val imageUrl: String?
) {
    fun toEntity(memberId: Long): Notification {
        return Notification(
            title = title,
            body = body,
            imageUrl = imageUrl,
            memberId = memberId
        )
    }
}
