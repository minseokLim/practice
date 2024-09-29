package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.SendMessageInput
import jakarta.validation.constraints.NotEmpty

data class SendMessageRequest(
    @get:NotEmpty
    val content: String,

    val chatRoomId: Long
) {
    fun toInput(): SendMessageInput {
        return SendMessageInput(
            content = content,
            chatRoomId = chatRoomId
        )
    }
}
