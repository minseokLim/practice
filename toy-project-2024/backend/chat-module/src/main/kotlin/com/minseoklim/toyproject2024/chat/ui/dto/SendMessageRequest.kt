package com.minseoklim.toyproject2024.chat.ui.dto

import com.minseoklim.toyproject2024.chat.application.dto.SendMessageInput
import jakarta.validation.constraints.NotEmpty

data class SendMessageRequest(
    @get:NotEmpty
    val content: String
) {
    fun toInput(chatRoomId: Long): SendMessageInput {
        return SendMessageInput(
            content = content,
            chatRoomId = chatRoomId
        )
    }
}
