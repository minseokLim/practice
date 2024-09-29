package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class ChatRoomDto private constructor(
    val id: Long,
    val members: List<MemberDto>,
    val creator: MemberDto
) {
    companion object {
        fun of(
            chatRoom: ChatRoom,
            members: Collection<QueryMemberOutput>
        ): ChatRoomDto {
            val memberIdToName = members.associate { it.id to it.name }
            return ChatRoomDto(
                id = checkNotNull(chatRoom.id),
                members = chatRoom.getMemberIds().map { MemberDto(it, memberIdToName.getValue(it)) },
                creator = MemberDto(chatRoom.creatorId, memberIdToName.getValue(chatRoom.creatorId))
            )
        }
    }

    data class MemberDto(
        val id: Int,
        val name: String
    )
}
