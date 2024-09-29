package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom

data class MakeChatRoomOutput private constructor(
    val id: Long,
    val memberIds: List<Int>,
    val creatorId: Int
) {
    companion object {
        fun from(chatRoom: ChatRoom): MakeChatRoomOutput {
            return MakeChatRoomOutput(
                id = checkNotNull(chatRoom.id),
                memberIds = chatRoom.getMemberIds(),
                creatorId = chatRoom.creatorId
            )
        }
    }
}
