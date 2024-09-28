package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Room
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class RoomDto private constructor(
    val id: Long,
    val members: List<MemberDto>,
    val creator: MemberDto
) {
    companion object {
        fun of(
            room: Room,
            members: Collection<QueryMemberOutput>
        ): RoomDto {
            val memberIdToName = members.associate { it.id to it.name }
            return RoomDto(
                id = checkNotNull(room.id),
                members = room.getMemberIds().map { MemberDto(it, memberIdToName.getValue(it)) },
                creator = MemberDto(room.creatorId, memberIdToName.getValue(room.creatorId))
            )
        }
    }

    data class MemberDto(
        val id: Int,
        val name: String
    )
}
