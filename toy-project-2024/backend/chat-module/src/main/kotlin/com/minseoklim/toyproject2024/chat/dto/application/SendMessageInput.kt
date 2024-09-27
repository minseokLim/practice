package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Message

data class SendMessageInput(
    val content: String,
    val roomId: Long
) {
    fun toEntity(memberId: Int): Message {
        return Message(
            content = content,
            roomId = roomId,
            memberId = memberId
        )
    }
}
