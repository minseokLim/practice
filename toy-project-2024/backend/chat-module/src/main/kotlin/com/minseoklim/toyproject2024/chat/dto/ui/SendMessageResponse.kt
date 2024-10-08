package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.SendMessageOutput

class SendMessageResponse private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val memberId: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun from(output: SendMessageOutput): SendMessageResponse {
            return SendMessageResponse(
                id = output.id,
                content = output.content,
                chatRoomId = output.chatRoomId,
                memberId = output.memberId,
                isDeleted = output.isDeleted
            )
        }
    }
}
