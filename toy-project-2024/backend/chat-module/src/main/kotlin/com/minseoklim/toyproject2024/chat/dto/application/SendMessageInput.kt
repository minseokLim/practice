package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Message

data class SendMessageInput(
    val content: String,
    val chatRoomId: Long
) {
    fun toEntity(memberId: Long): Message {
        return Message(
            content = content,
            chatRoomId = chatRoomId,
            memberId = memberId
        )
    }
}
