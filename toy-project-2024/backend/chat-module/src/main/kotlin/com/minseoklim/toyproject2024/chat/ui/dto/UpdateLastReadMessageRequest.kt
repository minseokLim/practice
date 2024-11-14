package com.minseoklim.toyproject2024.chat.ui.dto

import com.minseoklim.toyproject2024.chat.application.dto.UpdateLastReadMessageInput

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
