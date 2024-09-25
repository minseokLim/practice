package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Room

data class MakeRoomOutput private constructor(
    val id: Long,
    val memberIds: List<Int>,
    val creatorId: Int
) {
    companion object {
        fun from(room: Room): MakeRoomOutput {
            return MakeRoomOutput(
                id = checkNotNull(room.id),
                memberIds = room.getMemberIds(),
                creatorId = room.creatorId
            )
        }
    }
}
