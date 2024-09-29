package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.MakeChatRoomOutput

data class MakeChatRoomResponse private constructor(
    val id: Long,
    val memberIds: List<Int>,
    val creatorId: Int
) {
    companion object {
        fun from(output: MakeChatRoomOutput): MakeChatRoomResponse {
            return MakeChatRoomResponse(
                id = output.id,
                memberIds = output.memberIds,
                creatorId = output.creatorId
            )
        }
    }
}
