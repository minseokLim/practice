package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.SendMessageInput
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
