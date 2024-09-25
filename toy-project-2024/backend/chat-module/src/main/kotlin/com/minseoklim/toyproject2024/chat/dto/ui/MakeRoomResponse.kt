package com.minseoklim.toyproject2024.chat.dto.ui

import com.minseoklim.toyproject2024.chat.dto.application.MakeRoomOutput

data class MakeRoomResponse private constructor(
    val id: Long,
    val memberIds: List<Int>,
    val creatorId: Int
) {
    companion object {
        fun from(output: MakeRoomOutput): MakeRoomResponse {
            return MakeRoomResponse(
                id = output.id,
                memberIds = output.memberIds,
                creatorId = output.creatorId
            )
        }
    }
}
