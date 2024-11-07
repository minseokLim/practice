package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.UpdateLastReadMessageInput

data class UpdateLastReadMessageRequest(
    val lastReadMessageId: Long
) {
    fun toInput(
        memberId: Long,
        chatRoomId: Long
    ): UpdateLastReadMessageInput {
        return UpdateLastReadMessageInput(
            chatRoomId = chatRoomId,
            memberId = memberId,
            lastReadMessageId = lastReadMessageId
        )
    }
}
